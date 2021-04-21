package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Cylinder class represents three-dimensional cylinder in 3D Cartesian coordinate
 * system
 * Inherits tube since it is a non-infinite tube.
 * Implements Geometry interface through inheritance.
 *
 * @author ariel and mf.
 */
public class Cylinder extends Tube{
    /**
     * double representing height of cylinder
     */
    private double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point3D p1) {
        // we assume point is on cylinder so we check if it is on top
        // bottom or side.
        Point3D centerBottom = axisRay.getP0();
        Point3D centerTop = axisRay.getP0().add(axisRay.getDir().scale(height));
        if(p1.equals(centerTop) || isZero(p1.subtract(centerTop).dotProduct(axisRay.getDir()))) { // top
            return axisRay.getDir();
        } else if (p1.equals(centerBottom) || isZero(p1.subtract(centerBottom).dotProduct(axisRay.getDir()))) { // bottom
            return axisRay.getDir().scale(-1);
        } else { // side
            return super.getNormal(p1);
        }
    }

    @Override
    public String toString() {
        return "Cylinder: " + super.toString() + ", Height: " + height;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
