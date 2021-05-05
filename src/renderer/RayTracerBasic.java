package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

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
        var intersectionPoints = scene.geometries.findIntersections(ray);
        if(intersectionPoints == null) return scene.background;

        Point3D closestPoint = ray.findClosestPoint(intersectionPoints);
        return calcColor(closestPoint);
    }

    /**
     * calculate the color of a given point in the scene
     * @param p point on shape
     * @return color at p.
     */
    private Color calcColor(Point3D p) {
        return scene.ambientLight.getIntensity();
    }
}
