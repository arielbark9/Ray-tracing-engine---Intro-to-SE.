package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    /**
     * construct a ray from the camera to the pixel(i,j) to be traced.
     * @param nX number of width pixels
     * @param nY number of height pixels
     * @param j y coord of pixel to construct ray through
     * @param i x coord of pixel to construct ray through
     * @return ray through pixel(i,j).
     */
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

    /**
     * construct a list of rays from the camera to the pixel(i,j) to be traced.
     * this algorithm creates a grid within the pixel and randomly places rays inside each square
     * in the grid.
     * @param nX number of width pixels
     * @param nY number of height pixels
     * @param j y coord of pixel to construct ray through
     * @param i x coord of pixel to construct ray through
     * @param SSrays number of super sampling rays (root must be integer)
     * @return list of rays through pixel(i,j).
     */
    public List<Ray> constructRaysThroughPixel(int nX, int nY, int j, int i, int SSrays) {
        List<Ray> rays = new LinkedList<>();

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

        // Bottom Left of pixel
        Point3D bottomLeft = pIJ.add(vRight.scale(-rX/2)).add(vUp.scale(-rY/2));

        int nXY = (int)Math.sqrt(SSrays);
        double rXin = rX/nXY;
        double rYin = rY/nXY;
        Random offset = new Random(java.time.LocalDateTime.now().getSecond());
        for (int w = 0; w < nXY; w++) {
            for (int h = 0; h < nXY; h++) {
                rays.add(new Ray(pos,bottomLeft.add(vRight.scale((w + offset.nextDouble())*rXin))
                        .add(vUp.scale((h + offset.nextDouble())*rYin)).subtract(pos)));
            }
        }
        return rays;
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
