package geometries;


import primitives.BoundingBox;
import primitives.Point3D;
import primitives.Ray;
import geometries.Geometry;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class representing a collection of intersectable geometries.
 */
public class Geometries implements Intersectable {

    /**
     * collection of geometries.
     */
    private List<Intersectable> geometries;

    private BoundingBox boundingBox;

    public Geometries(){
        geometries = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<Intersectable>();
        this.geometries.addAll(Arrays.asList(geometries.clone()));
        calcBoundingBox();
    }

    /**
     * add geometries to list.
     * @param geometries composite to add
     */
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries.clone()));
        calcBoundingBox();
    }

    private void calcBoundingBox() {
        double minX,minY,minZ,maxX,maxY,maxZ;
        minX = minY = minZ = Double.POSITIVE_INFINITY;
        maxX = maxY = maxZ = Double.NEGATIVE_INFINITY;
        for (Intersectable intersectable: geometries) {
            Geometry geometry = (Geometry)intersectable;
            if(geometry.boundingBox.minX < minX) minX = geometry.boundingBox.minX;
            if(geometry.boundingBox.minY < minY) minY = geometry.boundingBox.minY;
            if(geometry.boundingBox.minZ < minZ) minZ = geometry.boundingBox.minZ;
            if(geometry.boundingBox.maxX > maxX) maxX = geometry.boundingBox.maxX;
            if(geometry.boundingBox.maxY > maxY) maxY = geometry.boundingBox.maxY;
            if(geometry.boundingBox.maxZ > maxZ) maxZ = geometry.boundingBox.maxZ;
        }
        boundingBox = new BoundingBox(minX,maxX,minY,maxY,minZ,maxZ);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        LinkedList<GeoPoint> intersections = new LinkedList<>();
        for (Intersectable g: geometries) {
            List<GeoPoint> temp = g.findGeoIntersections(ray,maxDistance);
            if(temp!=null)
                intersections.addAll(temp);
        }
        if(intersections.size() == 0)
            return null;
        return intersections;
    }
}
