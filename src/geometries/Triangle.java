package geometries;
import primitives.*;

import java.util.List;
import static primitives.Util.*;

/**
 * Triangle class represents two-dimensional triangle in 3D Cartesian coordinate
 * system
 * Inherits Polygon class since it is a polygon with 3 vertices.
 *
 * @author ariel and mf.
 */

public class Triangle extends Polygon{
    public Triangle(Point3D po1 ,Point3D po2 ,Point3D po3)
    {
        super(po1,po2,po3);
    }

    @Override
    public String toString() {
        return "Triangle: " +
                vertices.get(0).toString() + ',' +
                vertices.get(1).toString() + ',' +
                vertices.get(2).toString();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> points = plane.findIntersections(ray);

        Point3D p0 = points.get(0);

        Vector v1,v2,v3;
        try {
            v1 = vertices.get(0).subtract(p0); // v1 = p1-p0
            v2 = vertices.get(1).subtract(p0); // v2 = p2-p0
            v3 = vertices.get(2).subtract(p0); // v3 = p3-p0
        } catch (IllegalArgumentException ex) { // point on vertex because vector 0.
            return null;
        }

        Vector n1,n2,n3;
        try {
            n1 = v1.crossProduct(v2); // n1 = v1 x v2
            n2 = v2.crossProduct(v3); // n2 = v2 x v3
            n3 = v3.crossProduct(v1); // n3 = v3 x v1
        } catch (IllegalArgumentException ex) { // point on edge because vector 0.
            return null;
        }

        double vn1 = alignZero(ray.getDir().dotProduct(n1));
        double vn2 = alignZero(ray.getDir().dotProduct(n2));
        double vn3 = alignZero(ray.getDir().dotProduct(n3));

        if (isZero(vn1) || isZero(vn2) || isZero(vn3)) return null;
        if ((vn1 > 0 && vn2 > 0 && vn3 > 0) || (vn1 < 0 && vn2 < 0 && vn3 < 0)) {
            return points;
        }
        return null;
    }
}