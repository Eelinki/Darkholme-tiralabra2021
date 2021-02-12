package fi.eelij.Darkholme;

import fi.eelij.Darkholme.Domain.Point;
import fi.eelij.Darkholme.Domain.Triangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {
    @Test
    public void correctCircumcenter() {
        Point p1 = new Point(133.0, 58.0);
        Point p2 = new Point(146.0, 84.0);
        Point p3 = new Point(94.0, 84.0);
        Triangle t = new Triangle(p1, p2, p3);
        Point circumcenter = t.getCircumcenter();
        assertEquals(120.0, circumcenter.x, 0D);
        assertEquals(80.75, circumcenter.y, 0D);
    }
}
