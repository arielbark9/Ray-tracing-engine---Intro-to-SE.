package primitives;

/**
 * Class Point3D is the basic class representing a point of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Ariel
 */
public class Point3D {

    /**
     * (x,y,z) point.
     */
    Coordinate x,y,z;

    /**
     * Static constant zero value of point.
     */
    public static final Point3D ZERO = new Point3D(0,0,0);

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /**
     * add vector to point.
     * @param v vector to add to point.
     * @return point this + v.
     */
    public Point3D add(Vector v) {
        return new Point3D(x.coord + v.getHead().x.coord,y.coord + v.getHead().y.coord,z.coord + v.getHead().z.coord);
    }

    /**
     * get vector from this to p1
     * @param p1 head of vector point
     * @return 0-based vector this->p1.
     */
    public Vector subtract(Point3D p1) throws IllegalArgumentException {
        return new Vector(this.x.coord-p1.x.coord,this.y.coord-p1.y.coord,this.z.coord-p1.z.coord);
    }

    /**
     * @param p1 point to calc distance squared to
     * @return squared distance between this and p1
     */
    public double distanceSquared(Point3D p1) {
        var xDif = (this.x.coord - p1.x.coord);
        var yDif = (this.y.coord - p1.y.coord);
        var zDif = (this.z.coord - p1.z.coord);

        return  xDif*xDif + yDif*yDif + zDif*zDif;
    }

    /**
     * @param p1 point to calc distance to
     * @return distance between this and p1
     */
    public double distance(Point3D p1) {
        return Math.sqrt(distanceSquared(p1));
    }

    // region Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point3D)) return false;
        Point3D other = (Point3D) o;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }
    @Override
    public String toString() {
        return "P(" + x.toString() +
                "," + y.toString() +
                "," + z.toString() +
                ")";
    }

    public double getX() {
        return x.coord;
    }
    public double getY() {
        return y.coord;
    }
    public double getZ() {
        return z.coord;
    }
    // endregion
}
