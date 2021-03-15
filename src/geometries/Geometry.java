package geometries;
import primitives.Point3D;
import primitives.Vector;

/**
 * Geometry interface represents the capabilities of
 * two and three-dimensional shapes in 3D Cartesian coordinate system.
 *
 * @author ariel and mf.
 */
public interface Geometry {
    /**
     * calculates normal vector to a shape -
     * at the point provided if shape is 3D,
     * at any point if shape is 2D.
     * @param p1 point on shape
     * @return normal vector
     */
    public Vector getNormal(Point3D p1);
}
