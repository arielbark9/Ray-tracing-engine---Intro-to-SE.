package geometries;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * Geometry abstract class represents the capabilities of
 * two and three-dimensional shapes in 3D Cartesian coordinate system.
 *
 * @author ariel and mf.
 */
public abstract class Geometry implements Intersectable {
    /**
     * emission color of geometry
     */
    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * calculates normal vector to a shape -
     * at the point provided if shape is 3D,
     * at any point if shape is 2D.
     * @param p1 point on shape
     * @return normal vector
     */
    public abstract Vector getNormal(Point3D p1);

    /**
     * @return emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * builder pattern setter
     * @param emission color to set
     * @return this
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * builder pattern setter
     * @param material material of geometry
     * @return this
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
