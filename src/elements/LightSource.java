package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface representing a light source in the scene.
 */
public interface LightSource {
    /**
     * get intensity of light at a point
     * @param p point
     * @return intensity at p
     */
    Color getIntensity(Point3D p);

    /**
     * get normalized l vector from light to point
     * @param p point
     * @return l vector
     */
    Vector getL(Point3D p);
}
