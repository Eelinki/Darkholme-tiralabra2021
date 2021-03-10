package fi.eelij.Darkholme;

import fi.eelij.Darkholme.Domain.Edge;
import fi.eelij.Darkholme.Domain.Point;
import fi.eelij.Darkholme.Util.UniqueList;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniqueListTest {
    @Test
    public void cannotAddSameObjectTwice() {
        UniqueList<Edge> ul = new UniqueList<>();

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 3);
        Edge e1 = new Edge(p1, p2);

        assertTrue(ul.add(e1));
        assertFalse(ul.add(e1));
    }

    @Test
    public void cannotAddEqualObjectTwice() {
        UniqueList<Edge> ul = new UniqueList<>();

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 3);
        Edge e1 = new Edge(p1, p2);
        Edge e2 = new Edge(p1, p2);

        assertTrue(ul.add(e1));
        assertFalse(ul.add(e2));
    }

    @Test
    public void removeWorks() {
        UniqueList<Edge> ul = new UniqueList<>();

        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 3);
        Point p3 = new Point(5, 3);
        Edge e1 = new Edge(p1, p2);
        Edge e2 = new Edge(p1, p2);
        Edge e3 = new Edge(p1, p3);

        assertTrue(ul.add(e1));
        assertFalse(ul.add(e2));
        assertTrue(ul.add(e3));

        assertTrue(ul.remove(e3));
        assertFalse(ul.remove(e3));

        assertEquals(1, ul.size());
    }
}
