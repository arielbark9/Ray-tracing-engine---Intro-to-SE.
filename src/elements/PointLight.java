package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * point light source (like a bulb)
 */
public class PointLight extends Light implements LightSource{

    /**
     * position of light
     */
    private Point3D position;
    /**
     * attenuation factors (constant, linear, quadratic)
     */
    private double kC, kL, kQ;

    /**
     * @param intensity intensity of light
     * @param position position of light source
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
        kC = 1;
        kL = 0;
        kQ = 0;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double distance = p.distance(position);
        return getIntensity().reduce(kC + kL*distance + kQ*distance*distance);
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalize();
    }

    /**
     * builder pattern setter
     * @param kC constant attenuation factor
     * @return this
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * builder pattern setter
     * @param kL linear attenuation factor
     * @return this
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * builder pattern setter
     * @param kQ quadratic attenuation factor
     * @return this
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
