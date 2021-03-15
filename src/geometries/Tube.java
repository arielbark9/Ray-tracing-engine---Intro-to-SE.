package geometries;
import primitives.*;

/**
 * Tube class represents three-dimensional tube in 3D Cartesian coordinate
 * system
 * Implements Geometry interface.
 *
 * @author ariel and mf.
 */
public class Tube implements Geometry{

    /**
     * Ray that represents the axis in the center of the tube
     */
    protected Ray axisRay;
    /**
     * Radius of tube (from axis).
     */
    protected double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Tube: " +
                axisRay.toString() +
                ", R: " + radius;
    }

    @Override
    public Vector getNormal(Point3D p1) {
        return null;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }
}
