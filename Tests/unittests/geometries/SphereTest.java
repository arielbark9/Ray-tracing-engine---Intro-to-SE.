package unittests.geometries;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

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
}