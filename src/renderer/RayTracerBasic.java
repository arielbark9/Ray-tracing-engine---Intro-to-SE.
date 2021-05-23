package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * basic implementation of a ray tracer
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * Constructor to activate super constructor.
     * @param scene scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersectionPoints = scene.geometries.findGeoIntersections(ray);
        if(intersectionPoints == null) return scene.background;

        GeoPoint closestPoint = ray.findClosestGeoPoint(intersectionPoints);
        return calcColor(closestPoint,ray);
    }

    /**
     * calculate the color of a given point in the scene
     * @param gp point on shape
     * @return color at p.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return scene.ambientLight.getIntensity().add(gp.geometry.getEmission())
                // add calculated light contribution from all light sources
                .add(calcLocalEffects(gp,ray));
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
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
            if (ln * nv > 0 && unshaded(lightSource,l,n,intersection)) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, ln, lightIntensity),
                        calcSpecular(ks, l, n, ln, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, double ln, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * ln)).normalize();
        return lightIntensity.scale(ks * Math.pow(-v.dotProduct(r),nShininess));
    }

    private Color calcDiffusive(double kd, double ln, Color lightIntensity) {
        return lightIntensity.scale(kd * Math.abs(ln));
    }

    private static final double DELTA = 0.1;
    private boolean unshaded(LightSource lightSource,Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, lightSource.getDistance(geopoint.point));
        return intersections == null;
    }

}
