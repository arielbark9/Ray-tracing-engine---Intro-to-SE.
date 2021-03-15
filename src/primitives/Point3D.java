package primitives;

import primitives.Vector;

/**
 * Class Point3D is the basic class representing a point of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Ariel
 */
public class Point3D {
    Coordinate x;
    Coordinate y;
    Coordinate z;

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

    public static final Point3D ZERO = new Point3D(0,0,0);

    public Point3D add(Vector v) {
        return new Point3D(x.coord + v.getHead().x.coord,y.coord + v.getHead().y.coord,z.coord + v.getHead().z.coord);
    }

    public Vector subtract(Point3D p1) {
        try {
            return new Vector(this.x.coord-p1.x.coord,this.y.coord-p1.y.coord,this.z.coord-p1.z.coord);
        } catch (IllegalArgumentException e){
            throw e;
        }
    }

    public double distanceSquared(Point3D p1) {
        var xDif = (this.x.coord - p1.x.coord);
        var yDif = (this.y.coord - p1.y.coord);
        var zDif = (this.z.coord - p1.z.coord);

        return  xDif*xDif + yDif*yDif + zDif*zDif;
    }

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
        return "(" + x.toString() +
                "," + y.toString() +
                "," + z.toString() +
                ")";
    }
    // endregion
}
