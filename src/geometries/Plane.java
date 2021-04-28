package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.*;

/**
 * Plane class represents two-dimensional plane in 3D Cartesian coordinate
 * system
 * Implements Geometry interface.
 *
 * @author ariel and mf.
 */
public class Plane implements Geometry {

    /**
     * The perpendicular vector to the plane a.k.a. the normal.
     * (A,B,C) -> Ax + By + Cz + d = 0
     */
    private Vector normal;
    /**
     * The point on the plane that is at the tail of the normal.
     * (instead of (0,0,0)).
     */
    private Point3D q0;

    public Plane(Point3D p1, Point3D p2, Point3D p3) throws IllegalArgumentException {
        q0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        normal = v1.crossProduct(v2).normalize();
    }

    public Plane(Point3D p1 , Vector v1) {
        normal = v1.normalize();
        q0 = p1;
    }

    public Vector getNormal(Point3D p1) {
        return normal;
    }

    public Vector getNormal()
    {
        return normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        // the mathematical model as shown in recitation 3.
        double numerator;
        try {
            numerator = normal.dotProduct(q0.subtract(ray.getP0()));
        } catch (IllegalArgumentException ex) { // q0 == p0
            return null; // either included or no intersections so 0 points
        }
        double denominator = normal.dotProduct(ray.getDir());
        if(isZero(numerator) || isZero(denominator)) return null; // orthogonal || parallel
        double t = alignZero(numerator/denominator);
        if(t > 0) {
            LinkedList<Point3D> res = new LinkedList<>();
            res.add(ray.getPoint(t));
            return res;
        }
        return null;
    }
}
