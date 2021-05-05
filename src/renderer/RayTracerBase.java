package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Ray tracer abstract parent designed to trace rays in scene
 */
public abstract class RayTracerBase {
    /**
     * scene to trace ray in
     */
    protected Scene scene;

    /**
     * basic constructor
     * @param scene scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * trace ray in scene
     * @param ray traced ray
     * @return Color of traced point on the ray
     */
    public abstract Color traceRay(Ray ray);
}
