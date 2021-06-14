package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
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
        Point3D up = axisRay.getP0().add(axisRay.getDir().scale(height));
        Point3D down = axisRay.getP0();
        boundingBox = new BoundingBox(
                Math.min(Math.min(up.getX() + radius, up.getX() - radius),
                        Math.min(down.getX() + radius, down.getX() - radius)),
                Math.max(Math.max(up.getX() + radius, up.getX() - radius),
                        Math.max(down.getX() + radius, down.getX() - radius)),
                Math.min(Math.min(up.getY() + radius, up.getY() - radius),
                        Math.min(down.getY() + radius, down.getY() - radius)),
                Math.max(Math.max(up.getY() + radius, up.getY() - radius),
                        Math.max(down.getY() + radius, down.getY() - radius)),
                Math.min(Math.min(up.getZ() + radius, up.getZ() - radius),
                        Math.min(down.getZ() + radius, down.getZ() - radius)),
                Math.max(Math.max(up.getZ() + radius, up.getZ() - radius),
                       Math.max(down.getZ() + radius, down.getZ() - radius)));
    }

    @Override
    public Vector getNormal(Point3D p1) {
        // we assume point is on cylinder so we check if it is on top
        // bottom or side.
        Point3D centerBottom = axisRay.getP0();
        Point3D centerTop = axisRay.getPoint(height);
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
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = super.findGeoIntersections(ray, maxDistance);

        Point3D p0 = axisRay.getP0();
        Vector dir = axisRay.getDir();

        if (intersections != null) {
            List<GeoPoint> temp = new LinkedList<>();

            for (GeoPoint g : intersections) {
                double pointHeight = alignZero(g.point.subtract(p0).dotProduct(dir));
                if (pointHeight > 0 && pointHeight < height) {
                    temp.add(g);
                }
            }

            if (temp.isEmpty()) {
                intersections = null;
            }
            else {
                intersections = temp;
            }
        }

        List<GeoPoint> planeIntersection = new Plane(p0, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point.point.equals(p0) || alignZero(point.point.subtract(p0).lengthSquared() - radius * radius) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point.geometry = this;
                intersections.add(point);
            }
        }

        Point3D p1 = p0.add(dir.scale(height));

        planeIntersection = new Plane(p1, dir).findGeoIntersections(ray);
        if (planeIntersection != null) {
            GeoPoint point = planeIntersection.get(0);
            if (point.point.equals(p1) || alignZero(point.point.subtract(p1).lengthSquared() - radius * radius) < 0) {
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                point.geometry = this;
                intersections.add(point);
            }
        }

        if (intersections != null) {
            for (GeoPoint g : intersections) {
                g.geometry = this;
            }
        }

        return intersections;
    }
}
