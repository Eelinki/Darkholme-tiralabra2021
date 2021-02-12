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
    public String toString() {
        return x + ", " + y;
    }
}
