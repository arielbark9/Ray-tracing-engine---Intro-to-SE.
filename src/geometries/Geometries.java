package geometries;


import primitives.Point3D;
import primitives.Ray;

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

    public Geometries(){
        geometries = new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<Intersectable>();
        this.geometries.addAll(Arrays.asList(geometries.clone()));
    }

    /**
     * add geometries to list.
     * @param geometries composite to add
     */
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries.clone()));
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
