package geometries;

import primitives.*;

public class Tube implements Geometry{
    protected Ray axisRay;
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
