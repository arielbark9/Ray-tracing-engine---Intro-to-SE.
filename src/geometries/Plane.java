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
public class Plane extends Geometry {

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
        initializeBoundingBox(normal,q0);
    }

    public Plane(Point3D p1 , Vector v1) {
        normal = v1.normalize();
        q0 = p1;
        initializeBoundingBox(normal,q0);
    }

    /**
     * init bounding box for plane
     * @param normal normal to plane
     * @param q0 tail of plane normal point
     */
    private void initializeBoundingBox(Vector normal, Point3D q0) {
        if(normal.equals(new Vector(1,0,0))) {
            boundingBox = new BoundingBox(q0.getX()-0.0005d,q0.getX()+0.0005d,//
                    Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,//
                    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        } else if(normal.equals(new Vector(0,1,0))) {
            boundingBox = new BoundingBox(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,//
                    q0.getY()-0.0005d,q0.getY()+0.0005d,//
                    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        } else if(normal.equals(new Vector(0,0,1))) {
            boundingBox = new BoundingBox(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,//
                    Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,//
                    q0.getZ()-0.0005d, q0.getZ()+0.0005d);
        } else {
            boundingBox = new BoundingBox(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,//
                    Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,//
                    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
    }

    public Vector getNormal(Point3D p1) {
        return normal;
    }

    public Vector getNormal()
    {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
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
            LinkedList<GeoPoint> res = new LinkedList<>();
            res.add(new GeoPoint(this,ray.getPoint(t)));
            return res;
        }
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
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
        if(t > 0 && alignZero(t-maxDistance) <= 0) {
            LinkedList<GeoPoint> res = new LinkedList<>();
            res.add(new GeoPoint(this,ray.getPoint(t)));
            return res;
        }
        return null;
    }
}
