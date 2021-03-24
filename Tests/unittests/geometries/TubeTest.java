package unittests.geometries;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Tube class
 * @author Ariel
 */
public class TubeTest {
    /**
     * Test method for
     * {@link geometries.Tube#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        Tube tube = new Tube(new Ray(new Point3D(0,0,0),new Vector(0,0,1)),1d);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test normal to Tube (also tests that normal length is 1)
        var normal = tube.getNormal(new Point3D(-1,0,-1));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(-1,0,0), normal);
        // =============== Boundary Values Tests ==================
        // TC11: test normal when the segment connecting ray start and point on tube
        // is perpendicular to ray.
        normal = tube.getNormal(new Point3D(1,0,0));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(1,0,0), normal);
    }
}