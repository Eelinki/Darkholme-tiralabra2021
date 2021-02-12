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
        this.center = getCircumcircle();
        this.radius = getRadius();
    }

    public Point getCircumcircle() {
        double a = points[1].x - points[0].x;
        double b = points[1].y - points[0].y;
        double c = points[2].x - points[0].x;
        double d = points[2].y - points[0].y;
        double e = a * (points[0].x + points[1].x) + b * (points[0].y + points[1].y);
        double f = c * (points[0].x + points[2].x) + d * (points[0].y + points[2].y);
        double g = 2.0 * (a * (points[2].y - points[1].y) - b * (points[2].x - points[1].x));
        if (g == 0) {
            return null;
        }
        double px = (d * e - b * f) / g;
        double py = (a * f - c * e) / g;
        return new Point(px, py);
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

    @Override
    public String toString() {
        return "Triangle((" + points[0] + "), (" + points[1] + "), (" + points[2] + ")";
    }
}
