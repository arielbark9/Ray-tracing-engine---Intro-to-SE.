package geometries;

import primitives.*;

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

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        try
        {
            q0 = p1;
            Vector v1 = p2.subtract(p1);
            Vector v2 = p3.subtract(p1);
            normal = v1.crossProduct(v2).normalize();
        } catch (IllegalArgumentException ex) { throw ex; }
    }

    public Plane(Point3D p1 , Vector v1) {
        normal = v1;
        q0 = p1;
    }

    public Vector getNormal(Point3D p1) {
        return normal;
    }

    public Vector getNormal()
    {
        return normal;
    }
}
