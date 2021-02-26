package fi.eelij.Darkholme.Domain;

import fi.eelij.Darkholme.Util.CustomList;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;

public class Generator {
    private int width;
    private int height;
    private int[][] data;
    private CustomList<Triangle> triangles;
    private Point[] points;
    private Random r;
    private long seed;

    /**
     * Constructor
     *
     * @param width  Width of the map in pixels
     * @param height Height of the map in pixels
     * @param seed   Seed to be used in the generation
     */
    public Generator(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        this.data = new int[this.width][this.height];
        this.triangles = new CustomList<>();
        this.seed = seed;
    }

    public Generator(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new int[this.width][this.height];
        this.triangles = new CustomList<>();
    }

    /**
     * Generates the dungeon
     *
     * @param amount Amount of points to be generated
     */
    public void generate(int amount) {
        if(this.seed != 0) {
            this.r = new Random(seed);
        } else {
            this.r = new Random();
        }

        this.data = new int[this.width][this.height];

        Point[] points = pointCloud(amount);
        this.points = points;

        CustomList<Point> pointsList = new CustomList<>();

        for (Point p : points) {
            pointsList.add(p);
        }

        Delaunay bw = new Delaunay(points, width, height);
        this.triangles = new CustomList<>();
        for (Triangle t : bw.triangles) {
            this.triangles.add(t);
        }

        LinkedHashSet<Edge> bwEdges = bw.getEdges();
        MST k = new MST(bwEdges, pointsList);
        LinkedHashSet<Edge> mstEdges = k.getMST();

        LineGenerator lg = new LineGenerator(this.data);

        // Add few edges back from the Delaunay graph to create more corridors
        for (Triangle t : triangles) {
            for (Edge e : t.getEdges()) {
                if (r.nextDouble() < 0.15) {
                    lg.run(e);
                }
            }
        }

        for (Edge e : mstEdges) {
            lg.run(e);
        }

        // Create rooms
        for (Edge e : mstEdges) {
            for (Point p : e.points) {

                int roomX = r.nextInt(6) + 3;
                int roomY = r.nextInt(6) + 3;
                int roomW = r.nextInt(6) + 3;
                int roomH = r.nextInt(6) + 3;

                for (int i = (int) p.x - roomX; i < (int) p.x + roomW; i++) {
                    for (int j = (int) p.y - roomY; j < (int) p.y + roomH; j++) {
                        if (i < 0 || j < 0 || i > width - 1 || j > height - 1) {
                            continue;
                        }
                        this.data[i][j] = 1;
                    }
                }
            }
        }
    }

    /**
     * Randomly generate points into a two-dimensional array.
     *
     * @param amount The number of points to be generated.
     * @return Array of points
     */
    private Point[] pointCloud(int amount) {
        Point[] points = new Point[amount];

        for (int i = 0; i < amount; i++) {
            int x = r.nextInt((width - 2) + 1) + 1;
            int y = r.nextInt((height - 2) + 1) + 1;

            points[i] = new Point(x, y, r.nextDouble());
        }

        return points;
    }

    /**
     * Generates a WritableImage to visualize generated dungeon
     *
     * @param scale Scaling factor
     * @return Generated image
     */
    public WritableImage generateImage(int scale) {
        WritableImage image = new WritableImage(width * scale, height * scale);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color;
                if (data[i][j] == 1) {
                    color = new Color(1, 1, 1, 1);
                } else {
                    color = new Color(0, 0, 0, 1);
                }

                for (int k = 0; k < scale; k++) {
                    for (int l = 0; l < scale; l++) {
                        image.getPixelWriter().setColor(i * scale + k, j * scale + k, color);
                        image.getPixelWriter().setColor(i * scale + k, j * scale + l, color);
                    }
                }
            }
        }

        return image;
    }

    public String mapToString() {
        return Arrays.deepToString(this.data);
    }

    public String pointsToString() {
        return Arrays.toString(this.points);
    }
}
