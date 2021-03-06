package geometries;
import primitives.*;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

/**
 * Sphere class represents three-dimensional sphere in 3D Cartesian coordinate
 * system
 * Implements Geometry interface.
 *
 * @author ariel and mf.
 */
public class Sphere extends Geometry {

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
        double radiusE = radius + 0.0000001d; // radius plus a small number epsilon
        boundingBox = new BoundingBox(center.getX()-radiusE, center.getX()+radiusE,//
                center.getY()-radiusE, center.getY()+radiusE,//
                center.getZ()-radiusE, center.getZ()+radiusE);
    }

    @Override
    public Vector getNormal(Point3D p1) {
        // simple normal to sphere calculation.
        return p1.subtract(center).normalize();
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

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        // the mathematical model as shown in recitation 3.

        Vector u;
        try {
            u = center.subtract(ray.getP0());
        } catch (IllegalArgumentException ex) { // ray starts at center
            LinkedList<GeoPoint> res = new LinkedList<>();
            res.add(new GeoPoint(this,ray.getPoint(radius)));
            return res;
        }
        double tM = u.dotProduct(ray.getDir()); // projection of u on v.
        double d = Math.sqrt(u.lengthSquared() - (tM*tM));

        if (d-radius >= 0) return null; // ray is outside of sphere

        double tH = Math.sqrt(radius*radius - d*d);
        double t1 = alignZero(tM + tH);
        double t2 = alignZero(tM - tH);

        LinkedList<GeoPoint> res;
        if((t1 > 0 && alignZero(t1-maxDistance) <= 0) //
                || (t2 > 0 && alignZero(t2-maxDistance) <= 0)) { // this is done to not initialize for no reason.
            res = new LinkedList<>();
            if (t1 > 0 && alignZero(t1-maxDistance) <= 0) {
                Point3D p1 = ray.getPoint(t1);
                res.add(new GeoPoint(this,p1));
            }
            if (t2 > 0 && alignZero(t2-maxDistance) <= 0) {
                Point3D p2 = ray.getPoint(t2);
                res.add(new GeoPoint(this,p2));
            }
        } else return null;

        return res;
    }
}

