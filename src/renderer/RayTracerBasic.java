package renderer;

import elements.LightSource;
import geometries.Geometry;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * basic implementation of a ray tracer
 */
public class RayTracerBasic extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

    /**
     * Constructor to activate super constructor.
     * @param scene scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of a given point in the scene
     * @param gp point on shape
     * @param ray initial intersecting ray
     * @return color at p.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * calculate global and local effects using recursion
     * @param intersection intersection point + geometry
     * @param ray initial intersecting ray
     * @param level level of recursion
     * @param k attenuation factor
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray,k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * calculate global effects on point (refraction and reflection)
     * @param gp point of intersection
     * @param v initial intersecting ray
     * @param level level of recursion
     * @param k attenuation factor
     * @return global effects additive
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.getKr();
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.getKr(), kkr);
        double kkt = k * material.getKt();
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.getKt(), kkt));
        return color;
    }

    /**
     * calculate specific affect each time - refraction or reflection.
     * @param ray constructed reflected/refracted ray
     * @param level level of recursion
     * @param kx kR / kT
     * @param kkx kkR/ kkT
     * @return specific global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level-1, kkx)
        ).scale(kx);
    }

    /**
     * construct the refracted ray from geometry
     * @param point geometry's point
     * @param v initial intersection ray direction
     * @param n normal to geometry at point
     * @return refracted ray
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point,v,n);
    }

    /**
     * construct the reflected ray from geometry
     * @param point geometry's point
     * @param v initial intersection ray direction
     * @param n normal to geometry at point
     * @return reflected ray
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n))).normalize();
        return new Ray(point,r,n);
    }

    /**
     * calculate local effects of lights in scene
     * diffusive and specular
     * @param intersection point of intersection
     * @param ray initial intersecting ray
     * @return local effects on point in scene
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double ln = alignZero(n.dotProduct(l));
            if (ln * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, ln, lightIntensity),
                            calcSpecular(ks, l, n, ln, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * calculate specular part of light effect
     * @param ks specular attenuation factor
     * @param l vector from light to point
     * @param n normal to geometry at point
     * @param ln l.dotProduct(n)
     * @param v intersection ray's direction
     * @param nShininess material's shininess factor
     * @param lightIntensity light intensity
     * @return specular color additive
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double ln, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * ln)).normalize();
        return lightIntensity.scale(ks * Math.pow(Math.max(0, -v.dotProduct(r)),nShininess));
    }

    /**
     * calculate diffusive part of light effect
     * @param kd diffusive attenuation factor
     * @param ln l.dotProduct(n) | l - vector from light to point, n - normal to geometry at point
     * @param lightIntensity light intensity
     * @return diffusion color additive
     */
    private Color calcDiffusive(double kd, double ln, Color lightIntensity) {
        return lightIntensity.scale(kd * Math.abs(ln));
    }

    /**
     * check if a point in scene is not shaded from a specific light source
     * @param lightSource light source
     * @param l vector from light source to point
     * @param n normal to geometry at point
     * @param geopoint geometry and point
     * @return is not shaded
     */
    private boolean unshaded(LightSource lightSource,Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection,n);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, lightSource.getDistance(geopoint.point));

        if(intersections == null) return true;

        for (GeoPoint gp : intersections) {
            if(gp.geometry.getMaterial().getKt() == 0)
                return false;
        }
        return true;
    }

    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries
                .findGeoIntersections(lightRay,lightDistance);
        if (intersections == null) return 1d;

        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            ktr *= gp.geometry.getMaterial().getKt();
            if (ktr < MIN_CALC_COLOR_K) return 0.0;
        }
        return ktr;
    }

    /**
     * @param ray ray to intersect scene
     * @return closest intersection on ray to ray head
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if(intersections == null) return null;
        return ray.findClosestGeoPoint(intersections);
    }
}