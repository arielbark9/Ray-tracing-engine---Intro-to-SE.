package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder class represents three-dimensional cylinder in 3D Cartesian coordinate
 * system
 * Inherits tube since it is a non-infinite tube.
 * Implements Geometry interface through inheritance.
 *
 * @author ariel and mf.
 */
public class Cylinder extends Tube{
    /**
     * double representing height of cylinder
     */
    private double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point3D p1) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder: " + super.toString() + ", Height: " + height;
    }
}
