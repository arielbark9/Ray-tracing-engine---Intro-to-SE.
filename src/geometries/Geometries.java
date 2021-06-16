package geometries;


import primitives.Point3D;
import primitives.Ray;

import java.util.*;

/**
 * Composite class representing a collection of intersectable geometries.
 */
public class Geometries extends Intersectable {

    /**
     * collection of geometries.
     */
    private List<Intersectable> geometries;

    public Geometries(){
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<>();
        this.geometries.addAll(Arrays.asList(geometries.clone()));
        calcBoundingBox();
    }

    public Geometries(List<Intersectable> geometries){
        this.geometries = new LinkedList<>();
        this.geometries.addAll(geometries);
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

    /**
     * calculate bounding box for current geometries
     */
    private void calcBoundingBox() {
        double minX,minY,minZ,maxX,maxY,maxZ;
        minX = minY = minZ = Double.POSITIVE_INFINITY;
        maxX = maxY = maxZ = Double.NEGATIVE_INFINITY;
        for (Intersectable intersectable: geometries) {
            if(intersectable.boundingBox.getMin().getX() < minX)
                minX = intersectable.boundingBox.getMin().getX();
            if(intersectable.boundingBox.getMin().getY() < minY)
                minY = intersectable.boundingBox.getMin().getY();
            if(intersectable.boundingBox.getMin().getZ() < minZ)
                minZ = intersectable.boundingBox.getMin().getZ();
            if(intersectable.boundingBox.getMax().getX() > maxX)
                maxX = intersectable.boundingBox.getMax().getX();
            if(intersectable.boundingBox.getMax().getY() > maxY)
                maxY = intersectable.boundingBox.getMax().getY();
            if(intersectable.boundingBox.getMax().getZ() > maxZ)
                maxZ = intersectable.boundingBox.getMax().getZ();
        }
        boundingBox = new BoundingBox(minX,maxX,minY,maxY,minZ,maxZ);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        LinkedList<GeoPoint> intersections = new LinkedList<>();
        if(boundingBox != null && boundingBox.anyGeoIntersections(ray,maxDistance)) { // if intersects the AABB
            for (Intersectable intersectable : geometries) {
                List<GeoPoint> temp = intersectable.findGeoIntersections(ray,maxDistance);
                if(temp!=null)
                    intersections.addAll(temp);
            }
        }

        return intersections.size() == 0 ? null : intersections;
    }

    /**
     * Create BVH tree from composite with algorithm that divides
     * by longest axis each time until we reach a binary tree
     * @param composite composite geometries set to "sort"
     * @return BVH tree of composite
     */
    public Geometries createBVH(Geometries composite){
        int size = composite.geometries.size();
        if(size <= 2) return composite; //creates a binary tree

        char longestAxis;
        Point3D dif = composite.boundingBox.getMax().subtract(composite.boundingBox.getMin()).getHead();
        longestAxis = dif.getX()>dif.getY()?(dif.getX()>dif.getZ()?'X':'Z'):(dif.getY()>dif.getZ()?'Y':'Z');
        composite.sortAlongAxis(longestAxis);

        List<Geometries> geosLists = partitionList(composite,2);
        composite = new Geometries();
        for(Geometries geo : geosLists) {
            composite.add(new Geometries(createBVH(geo)));
        }
        return composite;
    }

    /**
     * partition geometries composite into n (almost) equal sets
     * @param composite composite
     * @param n num of partitions
     * @return List of partitioned composites
     */
    private List<Geometries> partitionList(Geometries composite, int n) {
        List<Geometries> res = new LinkedList<>();
        int size = composite.geometries.size();
        for (int i = 0; i < n-1; i++)
            res.add(new Geometries(composite.geometries.subList(i*(size+1)/n,(i+1)*(size+1)/n)));
        res.add(new Geometries(composite.geometries.subList((n-1)*(size+1)/n,size)));

        return res;
    }

    /**
     * sort a composite along a given axis.
     * the composite's position along the axis is determined by it's bounding box
     * axis average. (minAxis + maxAxis)/2
     * @param axis X,Y or Z char
     */
    private void sortAlongAxis(char axis) {
        switch (axis) {
            case 'X' -> this.geometries.sort(new SortByX());
            case 'Y' -> this.geometries.sort(new SortByY());
            case 'Z' -> this.geometries.sort(new SortByZ());
            default -> throw new IllegalArgumentException("axis must be X,Y or Z");
        }
    }
}

/**
 * a comparator to sort by X axis
 */
class SortByX implements Comparator<Intersectable>{
    @Override
    public int compare(Intersectable s1, Intersectable s2) {
        double s1X = (s1.boundingBox.getMin().getX()+s1.boundingBox.getMax().getX())/2;
        double s2X = (s2.boundingBox.getMin().getX()+s2.boundingBox.getMax().getX())/2;
        return Double.compare(s1X,s2X);
    }
}

/**
 * a comparator to sort by Y axis
 */
class SortByY implements Comparator<Intersectable>{
    @Override
    public int compare(Intersectable s1, Intersectable s2) {
        double s1Y = (s1.boundingBox.getMin().getY()+s1.boundingBox.getMax().getY())/2;
        double s2Y = (s2.boundingBox.getMin().getY()+s2.boundingBox.getMax().getY())/2;
        return Double.compare(s1Y,s2Y);
    }
}

/**
 * a comparator to sort by Z axis
 */
class SortByZ implements Comparator<Intersectable>{
    @Override
    public int compare(Intersectable s1, Intersectable s2) {
        double s1Z = (s1.boundingBox.getMin().getZ()+s1.boundingBox.getMax().getZ())/2;
        double s2Z = (s2.boundingBox.getMin().getZ()+s2.boundingBox.getMax().getZ())/2;
        return Double.compare(s1Z,s2Z);
    }
}
