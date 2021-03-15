package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
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
