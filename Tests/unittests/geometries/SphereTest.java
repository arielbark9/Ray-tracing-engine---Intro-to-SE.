package unittests.geometries;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Sphere class
 * @author Ariel
 */
public class SphereTest {
    /**
     * Test method for
     * {@link geometries.Sphere#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // TC01: Test normal to sphere (also tests that normal length is 1)
        var sp = new Sphere(new Point3D(1,0,0), 1);
        assertEquals("Error: getNormal() doesn't work correctly",
                new Vector(-1,0,0), sp.getNormal(new Point3D(0,0,0)));
    }

    @Test
    public void testFindIntersections() {
        int i = 0;
        var sp = new Sphere(new Point3D(0,0,0), 1);
        // EP rays for tests
        List<Ray> EPrayList = null;
        EPrayList.add(new Ray(new Point3D(2,0,0), new Vector(1,-0.2,0)));// no Intersections
        EPrayList.add(new Ray(new Point3D(2,0,0), new Vector(0,0,1)));// no Intersections
        EPrayList.add(new Ray(new Point3D(2,0,0), new Vector(-1,0.2,0)));
        EPrayList.add(new Ray(new Point3D(0.5,0,0), new Vector(-1,-0.5,0)));
       //BVA rays for test
        List<Ray> BVArayList = null;
        BVArayList.add(new Ray(new Point3D(1,0,0), new Vector(1,0,0)));
        BVArayList.add(new Ray(new Point3D(1,0,0), new Vector(-1,0,0)));
        BVArayList.add(new Ray(new Point3D(1,0,0), new Vector(-1,0.5,0)));
        BVArayList.add(new Ray(new Point3D(1,0,0), new Vector(1,-0.5,0)));
        BVArayList.add(new Ray(new Point3D(0,0,0), new Vector(1,0,0)));
        BVArayList.add(new Ray(new Point3D(0.5,0,0), new Vector(1,0,0)));
        BVArayList.add(new Ray(new Point3D(1,0,0), new Vector(0,1,0)));
        BVArayList.add(new Ray(new Point3D(1.5,0,0), new Vector(-1,0,0)));
        BVArayList.add(new Ray(new Point3D(1,-1,0), new Vector(0,1,0)));
        BVArayList.add(new Ray(new Point3D(1,1,0), new Vector(0,1,0)));// no Intersections
        BVArayList.add(new Ray(new Point3D(1.5,0,0), new Vector(0,1,0)));// no Intersections
        BVArayList.add(new Ray(new Point3D(1.5,0,0), new Vector(1,0,0)));// no Intersections
        while (i != 10)
            assertEquals("g",);



    }
}