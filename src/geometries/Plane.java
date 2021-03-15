package geometries;

import primitives.*;

public class Plane implements Geometry {
    Vector normal; // (A,B,C) -> Ax + By + Cz + d = 0
    Point3D q0; // the point that the normal vector gets out.

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        try
        {
            q0 = p1;
            /*Vector v1 = p2.subtract(p1);
            Vector v2 = p3.subtract(p1);
            normal = v1.crossProduct(v2).normalize();*/
            normal = null;
        } catch (IllegalArgumentException ex) { throw ex; }
    }

    public Plane(Point3D p1 , Vector v1) {
        normal = v1;
        q0 = p1;
    }

    public Vector getNormal(Point3D p1) {
        return null;
    }

    public Vector getNormal()
    {
        return normal;
    }
}
