package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Directional light (far away light only has direction and intensity)
 */
public class DirectionalLight extends Light implements LightSource{

    /**
     * direction where light is coming from
     */
    private Vector direction;

    /**
     * @param intensity intensity of light
     * @param direction direction where light is coming from
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return this.direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
