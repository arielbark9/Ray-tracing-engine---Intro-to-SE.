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
        list = new LinkedList<Intersectable>();
    }

//    public Geometries(Intersectable... geometries){
//        list = Arrays.asList(geometries.clone());
//    }
    public Geometries(Intersectable... geometries){
        list = new LinkedList<Intersectable>();
        list.addAll(Arrays.asList(geometries.clone()));
    }

    public void add(Intersectable... geometries){
        list.addAll(Arrays.asList(geometries.clone()));
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
        LinkedList<Point3D> intersections = new LinkedList<>();
        for (Intersectable g: list) {
            List<Point3D> temp = g.findIntersections(ray);
            if(temp!=null)
                intersections.addAll(temp);
        }
        if(intersections.size() == 0)
            return null;
        return intersections;
    }
}
