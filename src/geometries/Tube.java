package geometries;
import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

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
        Point3D o; // the point that p1 projects onto axis ray.
        // t = axis ray direction * ( vector p0p1 ). t is how much to scale dir by to get to o.
        double t = this.axisRay.getDir().dotProduct(p1.subtract(this.axisRay.getP0()));

        if(!isZero(t)) { // o = p0 + (t * dir) as explained above
            o = this.axisRay.getP0().add(this.axisRay.getDir().scale(t));
        } else { // if t is 0 than o is just p0.
            o = this.axisRay.getP0();
        }
        // normal is a normalized Op1.
        return p1.subtract(o).normalize();
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
