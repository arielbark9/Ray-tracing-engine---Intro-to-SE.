package geometries;

import primitives.*;

public class Sphere implements Geometry {
    private Point3D center;
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

