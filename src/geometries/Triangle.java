package geometries;

import primitives.*;

public class Triangle extends Polygon{
    public Triangle(Point3D po1 ,Point3D po2 ,Point3D po3)
    {
        super(po1,po2,po3);
    }

    @Override
    public Vector getNormal(Point3D p1) { return null; }

    @Override
    public String toString() {
        return "Triangle: " +
                vertices.get(0).toString() + ',' +
                vertices.get(1).toString() + ',' +
                vertices.get(2).toString();
    }
}













 /*boolean CheckPointInTriangle(Point3D P)//function that checks if point is in the triangle
    {

        Vector v0 = vertices.get(2).subtract(vertices.get(0));
        Vector v1 = vertices.get(1).subtract(vertices.get(0));
        Vector v2 = P.subtract(vertices.get(0));

        double dot00 = v0.dotProduct(v0);
        double dot01 = v0.dotProduct(v1);
        double dot02 = v0.dotProduct(v2);
        double dot11 = v1.dotProduct(v1);
        double dot12 = v1.dotProduct(v2);

       double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
       double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
       double v = (dot00 * dot12 - dot01 * dot02) * invDenom;

        return (u >= 0) && (v >= 0) && (u + v < 1);
    }
*/
