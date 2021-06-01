package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Tube class represents three-dimensional tube in 3D Cartesian coordinate
 * system
 * Implements Geometry interface.
 *
 * @author ariel and mf.
 */
public class Tube extends Geometry{

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
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        Vector d = ray.getDir();
        Vector v = axisRay.getDir();
        double dv = d.dotProduct(v);

        if (ray.getP0().equals(axisRay.getP0())) {
            if (isZero(dv)) {
                if (alignZero(maxDistance - radius) > 0)
                    return List.of(new GeoPoint(this, ray.getPoint(radius)));
                else
                    return null;
            }

            Vector dvv = v.scale(d.dotProduct(v));

            if (d.equals(dvv)) {
                return null;
            }
            double t = Math.sqrt(radius * radius / d.subtract(dvv).lengthSquared());
            if(alignZero(maxDistance-t) > 0)
                return List.of(new GeoPoint(this, ray.getPoint(t)));
            else
                return null;
        }

        Vector x = ray.getP0().subtract(axisRay.getP0());

        double xv = x.dotProduct(v);

        double a = 1 - dv * dv;
        double b = 2 * d.dotProduct(x) - 2 * dv * xv;
        double c = x.lengthSquared() - xv * xv - radius * radius;

        if (isZero(a)) {
            if (isZero(b)) {
                return null;
            }
            double t = -c / b;
            if(alignZero(maxDistance-t) > 0)
                return List.of(new GeoPoint(this, ray.getPoint(t)));
            else
                return null;
        }

        double delta = alignZero(b * b - 4 * a * c);

        if (delta <= 0)
            return null;

        List<GeoPoint> intersections = null;
        double t = alignZero(-(b + Math.sqrt(delta)) / (2 * a));
        if (t > 0 && alignZero(maxDistance-t) > 0) {
            intersections = new LinkedList<>();
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }
        t = alignZero(-(b - Math.sqrt(delta)) / (2 * a));
        if (t > 0 && alignZero(maxDistance-t) > 0) {
            if (intersections == null) {
                intersections = new LinkedList<>();
            }
            intersections.add(new GeoPoint(this, ray.getPoint(t)));
        }

        return intersections;
    }
}
