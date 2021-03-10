package fi.eelij.Darkholme.Domain;

import fi.eelij.Darkholme.Util.CustomList;
import fi.eelij.Darkholme.Util.UniqueList;

import java.util.Iterator;

public class Delaunay {
    private CustomList<Point> initialPoints;
    public CustomList<Triangle> triangles;

    public Delaunay(Point[] points, int width, int height) {
        this.initialPoints = new CustomList<>();
        this.triangles = new CustomList<>();

        Point a = new Point(0, 0);
        Point b = new Point(0, height);
        Point c = new Point(width, height);
        Point d = new Point(width, 0);

        Triangle t1 = new Triangle(a, b, c);
        Triangle t2 = new Triangle(a, c, d);

        triangles.add(t1);
        triangles.add(t2);

        initialPoints.add(a);
        initialPoints.add(b);
        initialPoints.add(c);
        initialPoints.add(d);

        for (Point p : points) {
            run(p);
        }

        //remove triangles that contains a vertex from the original super-triangle
        CustomList<Triangle> finalTriangles = new CustomList<>();
        Iterator<Triangle> iter = triangles.iterator();

        while (iter.hasNext()) {
            Triangle t = iter.next();

            for (Point p : t.points) {
                if (p == initialPoints.get(0)
                        || p == initialPoints.get(1)
                        || p == initialPoints.get(2)
                        || p == initialPoints.get(3)) {
                    iter.remove();
                    break;
                } else {
                    finalTriangles.add(t);
                    break;
                }
            }
        }

        triangles = finalTriangles;
    }

    public void run(Point p) {
        CustomList<Triangle> badTriangles = new CustomList<>();
        UniqueList<Edge> edges = new UniqueList<>();
        CustomList<Edge> toRemove = new CustomList<>();

        for (int i = triangles.size() - 1; i >= 0; i--) {
            Triangle tri = triangles.get(i);

            if (tri.inCircum(p)) {
                badTriangles.add(tri);
                triangles.remove(triangles.get(i));
            }
        }

        for (Triangle tri : badTriangles) {
            Edge e1 = new Edge(tri.points[0], tri.points[1]);
            Edge e2 = new Edge(tri.points[1], tri.points[2]);
            Edge e3 = new Edge(tri.points[0], tri.points[2]);
            if (!edges.add(e1)) {
                toRemove.add(e1);
            }
            if (!edges.add(e2)) {
                toRemove.add(e2);
            }
            if (!edges.add(e3)) {
                toRemove.add(e3);
            }
        }

        for (Edge e : toRemove) {
            edges.remove(e);
        }

        for (Edge e : edges) {
            Triangle tri = new Triangle(e.points[0], e.points[1], p);
            triangles.add(tri);
        }
    }

    public UniqueList<Edge> getEdges() {
        UniqueList<Edge> edges = new UniqueList<>();

        for (Triangle t : triangles) {
            for (Edge e : t.getEdges()) {
                edges.add(e);
            }
        }

        return edges;
    }
}
