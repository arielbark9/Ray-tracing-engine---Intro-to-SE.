package primitives;

/**
 * PDS representing an Axis-Aligned conservative
 * bounding region for a geometry.
 */
public class BoundingBox {
    /**
     * 3D bounding region parameters
     * that define a box in 3D space.
     */
    public double minX,maxX;
    public double minY,maxY;
    public double minZ,maxZ;

    /**
     * constructor to init the fields.
     */
    public BoundingBox(double minX,double maxX, double minY, double maxY, double minZ,double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }
}
