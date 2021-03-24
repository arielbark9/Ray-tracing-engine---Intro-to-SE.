package unittests.geometries;

import geometries.Cylinder;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Ariel
 */
public class CylinderTest {
    /**
     * Test method for
     * {@link geometries.Cylinder#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        Cylinder cylinder = new Cylinder(new Ray(new Point3D(0,0,-1),new Vector(0,0,1)),2d,2);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test normal to cylinder at side (also tests that normal length is 1)
        var normal = cylinder.getNormal(new Point3D(2,0,0));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(1,0,0), normal);

        // TC02: Test normal to cylinder at bottom base
        normal = cylinder.getNormal(new Point3D(1,0,-1));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(0,0,-1), normal);

        // TC03: Test normal to cylinder at top base
        normal = cylinder.getNormal(new Point3D(1,0,1));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(0,0,1), normal);
        // =============== Boundary Values Tests ==================

        // TC11: test normal at bottom base at center
        normal = cylinder.getNormal(new Point3D(0,0,-1));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(0,0,-1), normal);

        // TC12: test normal at top base at the center
        normal = cylinder.getNormal(new Point3D(0,0,1));
        assertEquals("Error: getNormal() doesn't work correctly.", //
                new Vector(0,0,1), normal);

    }
}