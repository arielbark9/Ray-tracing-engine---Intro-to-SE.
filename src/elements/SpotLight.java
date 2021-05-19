package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * spot light source
 */
public class SpotLight extends PointLight{

    /**
     * direction of spot light
     */
    private Vector direction;

    /**
     * @param intensity intensity of light
     * @param position  position of light source
     * @param direction direction of spot light
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D p) {
        Color intensity = super.getIntensity(p);
        return intensity.scale(Math.max(0d,direction.dotProduct(getL(p))));
    }

    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
}
