package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;
import static primitives.Util.random;

public class Camera {
    /**
     * Point representing position of the camera
     */
    private Point3D pos;

    /**
     * Vectors representing orientation of camera
     * To - vector to view plane.
     */
    private Vector vTo, vUp, vRight;

    /**
     * view plane parameters.
     * height x width, distance from camera.
     */
    private double height, width, distance;

    public Camera(Point3D pos, Vector vTo, Vector vUp) throws IllegalArgumentException {
        this.pos = pos;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        if(!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("Error! vTo and vUp must be orthogonal");
        }
        vRight = vTo.crossProduct(vUp);
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        // pIJ = pCenter
        Point3D pIJ = pos.add(vTo.scale(distance));
        // Ratio
        double rX = width/nX;
        double rY = height/nY;
        // Xj, Yi
        double xJ = (j - (double)(nX-1)/2) * rX;
        double yI = -(i - (double)(nY-1)/2) * rY;
        // adding to pCenter
        if(!isZero(xJ)) pIJ = pIJ.add(vRight.scale(xJ));
        if(!isZero(yI)) pIJ = pIJ.add(vUp.scale(yI));
        // p0 = position of camera, dir = pIJ - pos.
        return new Ray(pos, pIJ.subtract(pos));
    }

    // region getters and setters
    public Camera setViewPlaneSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Camera setPosition(Point3D p0) {
        pos = p0;
        return this;
    }

    public Point3D getPos() {
        return pos;
    }

    public Vector getVto() {
        return vTo;
    }

    public Vector getVup() {
        return vUp;
    }

    public Vector getVright() {
        return vRight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }
    // endregion
}
