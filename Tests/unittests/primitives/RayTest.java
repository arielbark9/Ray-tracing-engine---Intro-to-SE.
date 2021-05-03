package unittests.primitives;

import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

public class RayTest {

    @Test
    public void findClosestPoint() {
        Ray ray = new Ray(new Point3D(1,0,0), new Vector(1,0,0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: a point in the middle of list is closest
        assertEquals("findClosestPoint() doesn't work well!",
                new Point3D(2,0,0), ray.findClosestPoint(List.of(
                        new Point3D(4,0,0),
                        new Point3D(5,0,0),
                        new Point3D(2,0,0),
                        new Point3D(6,0,0))));

        // =============== Boundary Values Tests ==================

        // TC11: empty list
        assertNull("findClosestPoint() doesn't work well!", ray.findClosestPoint(List.of()));

        // TC12: first point is closest
        assertEquals("findClosestPoint() doesn't work well!",
                new Point3D(2,0,0), ray.findClosestPoint(List.of(
                        new Point3D(2,0,0),
                        new Point3D(5,0,0),
                        new Point3D(4,0,0),
                        new Point3D(6,0,0))));

        // TC13: first point is closest
        assertEquals("findClosestPoint() doesn't work well!",
                new Point3D(2,0,0), ray.findClosestPoint(List.of(
                        new Point3D(4,0,0),
                        new Point3D(5,0,0),
                        new Point3D(6,0,0),
                        new Point3D(2,0,0))));
    }
}