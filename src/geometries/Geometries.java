package geometries;


import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> list;

    public Geometries(){
        list = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries){
        list = Arrays.asList(geometries.clone());
    }

    public void add(Intersectable... geometries){
        int i = geometries.length - 1;
        while(i + 1 != 0)
        {
            list.add(geometries[i]);
            i--;
        }
    }




    /**
     * finds all intersections between a shape and a ray.
     * <p>
     * we've chosen a linked list implementation because of adding complexity and
     * lack of need for random access.
     *
     * @param ray the ray to intersect with
     * @return list of intersection points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        ArrayList<Point3D> mergedList = new ArrayList<>();
        int length = list.size();
        try {
            while (length != 0) {
                List<Point3D> TempList = list.get(length - 1).findIntersections(ray);
                if (TempList != null )
                    mergedList.addAll(TempList);
                length--;
            }
        } catch (IllegalArgumentException ex) {
            return null;
        }
        if(mergedList.size() == 0)
            return null;
        return mergedList;
    }
}
