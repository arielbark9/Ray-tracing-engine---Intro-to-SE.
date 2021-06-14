package geometries;


import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class representing a collection of intersectable geometries.
 */
public class Geometries extends Intersectable {

    /**
     * collection of geometries.
     */
    private List<Intersectable> geometries;

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
            if(intersectable.boundingBox.minX < minX) minX = intersectable.boundingBox.minX;
            if(intersectable.boundingBox.minY < minY) minY = intersectable.boundingBox.minY;
            if(intersectable.boundingBox.minZ < minZ) minZ = intersectable.boundingBox.minZ;
            if(intersectable.boundingBox.maxX > maxX) maxX = intersectable.boundingBox.maxX;
            if(intersectable.boundingBox.maxY > maxY) maxY = intersectable.boundingBox.maxY;
            if(intersectable.boundingBox.maxZ > maxZ) maxZ = intersectable.boundingBox.maxZ;
        }
        boundingBox = new BoundingBox(minX,maxX,minY,maxY,minZ,maxZ);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        LinkedList<GeoPoint> intersections = new LinkedList<>();
        if(boundingBox.anyGeoIntersections(ray,maxDistance)) {
            for (Intersectable intersectable : geometries) {
                List<GeoPoint> temp = intersectable.findGeoIntersections(ray,maxDistance);
                if(temp!=null)
                    intersections.addAll(temp);
            }
        }

        /*
        for (Intersectable g: geometries) {
            List<GeoPoint> temp = g.findGeoIntersections(ray,maxDistance);
            if(temp!=null)
                intersections.addAll(temp);
        }
        if(intersections.size() == 0)
            return null;*/
        return intersections;
    }
}
