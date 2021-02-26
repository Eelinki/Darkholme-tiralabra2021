package fi.eelij.Darkholme;

import fi.eelij.Darkholme.Domain.Point;
import fi.eelij.Darkholme.Domain.Triangle;
import fi.eelij.Darkholme.Util.CustomList;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CustomListTest {
    @Test
    public void returnCorrectAmountOfItems() {
        CustomList<String> cl = new CustomList<>();
        cl.add("test1");
        assertEquals(1, cl.size());
        cl.add("test2");
        assertEquals(2, cl.size());
    }

    @Test
    public void customListReturnsCorrectItem() {
        CustomList<Point> cl = new CustomList<>();

        Point testObj = new Point(1, 2);

        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));
        cl.add(testObj);
        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));

        assertSame(testObj, cl.get(2));
        assertNotSame(testObj, cl.get(4));
    }

    @Test
    public void itemCanBeRemoved() {
        CustomList<Point> cl = new CustomList<>();

        Point testObj = new Point(1, 2);

        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));
        cl.add(testObj);
        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));

        assertEquals(5, cl.size());
        assertTrue(cl.remove(testObj));
        assertEquals(4, cl.size());
        assertFalse(cl.remove(testObj));
    }

    @Test
    public void cannotRemoveNonexistantItem() {
        CustomList<Point> cl = new CustomList<>();

        Point testObj = new Point(1, 2);

        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));
        cl.add(new Point(1, 2));

        assertEquals(4, cl.size());
        assertFalse(cl.remove(testObj));
        assertEquals(4, cl.size());
    }

    @Test
    public void correctOrderAfterRemoval() {
        CustomList<Point> cl = new CustomList<>();

        Point testObj1 = new Point(1, 1);
        Point testObj2 = new Point(2, 2);
        Point testObj3 = new Point(3, 3);
        Point testObj4 = new Point(4, 4);

        cl.add(testObj1);
        cl.add(testObj2);
        cl.add(testObj3);
        cl.add(testObj4);

        assertTrue(cl.remove(testObj3));
        assertSame(testObj1, cl.get(0));
        assertSame(testObj2, cl.get(1));
        assertSame(testObj4, cl.get(2));
    }

    @Test
    public void customListGrowsCorrectly() throws NoSuchFieldException, IllegalAccessException {
        Field list = CustomList.class.getDeclaredField("list");
        list.setAccessible(true);

        CustomList<Point> cl = new CustomList<>();
        Object[] clList = (Object[]) list.get(cl);

        assertEquals(8, clList.length);

        for (int i = 0; i < 10; i++) {
            cl.add(new Point(1, 2));
        }

        clList = (Object[]) list.get(cl);
        assertEquals(16, clList.length);
    }

    @Test
    public void customListShrinksCorrectly() throws NoSuchFieldException, IllegalAccessException {
        Field list = CustomList.class.getDeclaredField("list");
        list.setAccessible(true);

        CustomList<Point> cl = new CustomList<>();

        Object[] clList = (Object[]) list.get(cl);
        assertEquals(8, clList.length);

        for (int i = 0; i < 10; i++) {
            cl.add(new Point(1, 2));
        }

        clList = (Object[]) list.get(cl);
        assertEquals(16, clList.length);

        for (int i = 1; i < 5; i++) {
            cl.remove(cl.get(i));
        }


        clList = (Object[]) list.get(cl);
        assertEquals(8, clList.length);
    }

    @Test
    public void iteratorWorksCorrectly() {
        CustomList<Point> cl = new CustomList<>();

        Point testObj1 = new Point(1, 1);
        Point testObj2 = new Point(2, 2);
        Point testObj3 = new Point(3, 3);
        Point testObj4 = new Point(4, 4);

        cl.add(testObj1);
        cl.add(testObj2);
        cl.add(testObj3);
        cl.add(testObj4);

        assertSame(testObj1, cl.get(0));
        assertSame(testObj2, cl.get(1));
        assertSame(testObj3, cl.get(2));
        assertSame(testObj4, cl.get(3));

        Iterator<Point> iter = cl.iterator();

        while (iter.hasNext()) {
            Point p = iter.next();

            if (p == testObj2) {
                iter.remove();
            }
        }

        assertEquals(3, cl.size());
        assertSame(testObj1, cl.get(0));
        assertSame(testObj3, cl.get(1));
        assertSame(testObj4, cl.get(2));
    }

    @Test public void removalWorksInIterator() {
        CustomList<Triangle> cl = new CustomList<>();

        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(4, 4);

        Triangle testObj1 = new Triangle(p1, p4, p3);
        Triangle testObj2 = new Triangle(p1, p2, p3);
        Triangle testObj3 = new Triangle(p1, p4, p3);
        Triangle testObj4 = new Triangle(p1, p4, p3);

        cl.add(testObj1);
        cl.add(testObj2);
        cl.add(testObj3);
        cl.add(testObj4);

        assertSame(testObj1, cl.get(0));
        assertSame(testObj2, cl.get(1));
        assertSame(testObj3, cl.get(2));
        assertSame(testObj4, cl.get(3));

        Iterator<Triangle> iter = cl.iterator();

        while (iter.hasNext()) {
            Triangle t = iter.next();

            for (Point p : t.points) {
                if (p.x == 2) {
                    iter.remove();
                    break;
                }
            }
        }

        assertEquals(3, cl.size());
        assertSame(testObj1, cl.get(0));
        assertSame(testObj3, cl.get(1));
        assertSame(testObj4, cl.get(2));

        for (int i = 0; i < cl.size(); i++) {
            System.out.println(cl.get(i));
        }
    }
}
