package geometries;
import primitives.Ray;
import primitives.Point3D;
import java.util.List;


/**
 * a shape that is intesectable by a ray.
 */
public interface Intersectable {
    /**
     * finds all intersections between a shape and a ray.
     *
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray the ray to intersect with
     * @return list of intersection points
     */
    List<Point3D> findIntersections(Ray ray);
}
