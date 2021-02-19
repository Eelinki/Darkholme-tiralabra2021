package fi.eelij.Darkholme.Domain;

public class Triangle {
    public Point[] points;
    public Point center;
    public double radius;

    public Triangle(final Point p1, final Point p2, final Point p3) {
        this.points = new Point[3];
        this.points[0] = p1;
        this.points[1] = p2;
        this.points[2] = p3;
        this.center = getCircumcenter();
        this.radius = getRadius();
    }

    /**
     * Calculate cartesian coordinates for circumcenter U
     *
     * @return Point circumcenter of triangle
     */
    public Point getCircumcenter() {
        double ax = points[0].x;
        double ay = points[0].y;
        double bx = points[1].x;
        double by = points[1].y;
        double cx = points[2].x;
        double cy = points[2].y;
        double d = 2.0 * (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by));
        double ux = ((ax * ax + ay * ay) * (by - cy) + (bx * bx + by * by) * (cy - ay) + (cx * cx + cy * cy) * (ay - by)) / d;
        double uy = ((ax * ax + ay * ay) * (cx - bx) + (bx * bx + by * by) * (ax - cx) + (cx * cx + cy * cy) * (bx - ax)) / d;

        return new Point(ux, uy);
    }

    public double getRadius() {
        double x = points[0].x - center.x;
        double y = points[0].y - center.y;

        return Math.sqrt(x * x + y * y);
    }

    public boolean inCircum(final Point p) {
        double x = p.x - center.x;
        double y = p.y - center.y;

        return Math.sqrt(x * x + y * y) < radius;
    }

    public Edge[] getEdges() {
        Edge[] edges = new Edge[3];

        edges[0] = new Edge(points[0], points[1]);
        edges[1] = new Edge(points[1], points[2]);
        edges[2] = new Edge(points[0], points[2]);

        return edges;
    }

    @Override
    public String toString() {
        return "Triangle((" + points[0] + "), (" + points[1] + "), (" + points[2] + ")";
    }
}
