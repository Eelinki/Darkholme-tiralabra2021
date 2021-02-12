package fi.eelij.Darkholme.Domain;

public class Edge {
    public Point[] points;

    public Edge(Point p1, Point p2) {
        this.points = new Point[2];
        this.points[0] = p1;
        this.points[1] = p2;
    }

    @Override
    public int hashCode() {
        int hash = points[0].hashCode();
        hash = hash * points[1].hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }
}
