package primitives;

import primitives.*;

public class Ray {
    private Point3D p0;
    private Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
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
