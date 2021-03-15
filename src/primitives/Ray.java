package primitives;

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
}
