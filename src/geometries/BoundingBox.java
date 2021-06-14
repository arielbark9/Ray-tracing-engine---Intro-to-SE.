package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.min;
import static java.lang.Math.max;
import static primitives.Util.alignZero;

/**
 * class representing an Axis-Aligned conservative
 * bounding region for a geometry.
 */
public class BoundingBox extends Intersectable{
    /**
     * 3D bounding region parameters
     * that define a box in 3D space.
     */
    private Point3D min,max;

    /**
     * constructor to init the fields.
     */
    public BoundingBox(double minX,double maxX, double minY, double maxY, double minZ,double maxZ) {
        min = new Point3D(minX,minY,minZ);
        max = new Point3D(maxX,maxY,maxZ);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return null;
    }

    @Override
    public Boolean anyGeoIntersections(Ray ray, double maxDistance) {
        // r.dir is unit direction vector of ray
        Vector dirfrac = new Vector(1d/ray.getDir().getHead().getX(),//
                1d/ray.getDir().getHead().getY(),//
                1d/ray.getDir().getHead().getZ());
        // lb is the corner of AABB with minimal coordinates - left bottom, rt is maximal corner
        // ray.getP0() is origin of ray
        double t1 = (min.getX() - ray.getP0().getX())*dirfrac.getHead().getX();
        double t2 = (max.getX() - ray.getP0().getX())*dirfrac.getHead().getX();
        double t3 = (min.getY() - ray.getP0().getY())*dirfrac.getHead().getY();
        double t4 = (max.getY() - ray.getP0().getY())*dirfrac.getHead().getY();
        double t5 = (min.getZ() - ray.getP0().getZ())*dirfrac.getHead().getZ();
        double t6 = (max.getZ() - ray.getP0().getZ())*dirfrac.getHead().getZ();

        double tmin = max(Math.max(min(t1, t2), min(t3, t4)), min(t5, t6));
        double tmax = min(min(max(t1, t2), max(t3, t4)), max(t5, t6));

        // if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
        if (tmax < 0)
            return false;

        // if tmin > tmax, ray doesn't intersect AABB
        if (tmin > tmax)
            return false;

        return !(alignZero(maxDistance - tmin) < 0);
    }

    public Point3D getMax() {
        return max;
    }

    public Point3D getMin() {
        return min;
    }
}
