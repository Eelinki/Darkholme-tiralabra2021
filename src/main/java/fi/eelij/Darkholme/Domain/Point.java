package fi.eelij.Darkholme.Domain;

public class Point {
    public double x, y, value;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, double value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Point comparePoint = (Point) obj;

        if (this.x != comparePoint.x || this.y != comparePoint.y || this.value != comparePoint.value) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
