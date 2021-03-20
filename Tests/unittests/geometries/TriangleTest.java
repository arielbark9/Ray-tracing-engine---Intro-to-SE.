package unittests.geometries;

import geometries.Triangle;
import org.junit.Before;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void testGetNormal() {
        Triangle tr = new Triangle(new Point3D(0,0,0), //
                new Point3D(1,0,0), //
                new Point3D(0,1,0));
        assertEquals("Error: getNormal() does not return the right vector!",
                new Vector(0,0,1), tr.getNormal(new Point3D(1,0,0)));
    }
}