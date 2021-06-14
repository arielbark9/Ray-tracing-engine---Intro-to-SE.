package geometries;
import primitives.Ray;
import primitives.Point3D;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * a shape that is intesectable by a ray.
 */
public abstract class Intersectable {
    /**
     * every intersectable object also has a bounding box
     */
    protected BoundingBox boundingBox;

    /**
     * Geometry and Point PDS.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * constructor
         * @param geometry geo
         * @param point point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;

            return point.equals(geoPoint.point) && geometry == geoPoint.geometry;
        }
    }

    /**
     * finds all intersections between a shape and a ray.
     *
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray the ray to intersect with
     * @return list of intersection points
     */
    public List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    /**
     * finds all intersections between a shape and a ray.
     *
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray the ray to intersect with
     * @return list of intersection points with corresponding geometries
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
    }

    /**
     * finds intersections between a shape and a ray where distance to ray head
     * is smaller than max distance
     *
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray the ray to intersect with
     * @param maxDistance maximum distance to ray head for returned points.
     * @return list of intersection points with corresponding geometries
     */
    public abstract List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance);

    /**
     * finds if there are intersections between a shape and a ray where distance to ray head
     * is smaller than max distance
     *
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray the ray to intersect with
     * @param maxDistance maximum distance to ray head for returned points.
     * @return list of intersection points with corresponding geometries
     */
    public Boolean anyGeoIntersections(Ray ray, double maxDistance){
        if(findGeoIntersections(ray, maxDistance) != null)
            return true;
        return false;
    }
}
