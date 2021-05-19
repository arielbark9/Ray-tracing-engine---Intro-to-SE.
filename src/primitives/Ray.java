package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Class Ray is the class representing a ray of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Ariel
 */
public class Ray {
    /**
     * Point representing the start of the ray.
     */
    private Point3D p0;
    /**
     * Vector representing the direction of the ray.
     * dir is a normalized vector
     */
    private Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize(); // make sure vector is normalized
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        if (getP0() != null ? !getP0().equals(ray.getP0()) : ray.getP0() != null) return false;
        return getDir() != null ? getDir().equals(ray.getDir()) : ray.getDir() == null;
    }
    @Override
    public String toString() {
        return "Ray: " + p0.toString() + ", " + dir.toString();
    }

    public Point3D getP0() {
        return p0;
    }
    public Vector getDir() {
        return dir;
    }

    /**
     * @param t distance from head of ray to point
     * @return p0 + (t * direction vector)
     */
    public Point3D getPoint(double t) {
        return p0.add(dir.scale(t));
    }

    /**
     * @param points points on ray
     * @return closest point to ray head, p0
     */
    public Point3D findClosestPoint(List<Point3D> points) {
        Point3D res = null;
        if(!points.isEmpty()) {
            res = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                Point3D current = points.get(i);
                if(p0.distance(current) < p0.distance(res))
                    res = current;
            }
        }
        return res;
    }

    /**
     * @param points points on ray
     * @return closest geo point to ray head, p0
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        GeoPoint res = null;
        if(!points.isEmpty()) {
            res = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                GeoPoint current = points.get(i);
                if(p0.distance(current.point) < p0.distance(res.point))
                    res = current;
            }
        }
        return res;
    }
}
