package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
        return calcColor(closestPoint);
    }

    /**
     * calculate the color of a given point in the scene
     * @param gp point on shape
     * @return color at p.
     */
    private Color calcColor(GeoPoint gp) {
        return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
    }
}
