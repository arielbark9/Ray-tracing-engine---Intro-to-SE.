package geometries;
import primitives.*;

/**
 * Sphere class represents three-dimensional sphere in 3D Cartesian coordinate
 * system
 * Implements Geometry interface.
 *
 * @author ariel and mf.
 */
public class Sphere implements Geometry {

    /**
     * Point3D that represents center of sphere
     */
    private Point3D center;
    /**
     * double variable representing the radius of the sphere
     */
    private double radius;

    public Sphere(Point3D center, double r) {
        this.center = center;
        this.radius = r;
    }

    @Override
    public Vector getNormal(Point3D p1) {
        return null;
    }

    @Override
    public String toString() {
        return "Sphere: " + center.toString() + ", R: " + radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
}

