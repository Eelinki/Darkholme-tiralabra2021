package fi.eelij.Darkholme.Domain;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generator {
    private int width;
    private int height;
    private int[][] data;
    private int seed;
    private ArrayList<Triangle> triangles;

    /**
     * Constructor
     *
     * @param width  Width of the map in pixels
     * @param height Height of the map in pixels
     * @param seed   Seed to be used in the generation
     */
    public Generator(int width, int height, int seed) {
        this.width = width;
        this.height = height;
        this.data = new int[this.width][this.height];
        this.seed = seed;
        this.triangles = new ArrayList<>();
    }

    public Generator(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new int[this.width][this.height];
    }

    /**
     * Generates the dungeon
     *
     * @param amount Amount of points to be generated
     */
    public void generate(int amount) {
        this.data = new int[this.width][this.height];

        Point[] points = pointCloud(amount);

        Delaunay bw = new Delaunay(points, width, height);
        this.triangles = bw.triangles;
    }

    /**
     * Randomly generate points into a two-dimensional array.
     *
     * @param amount The number of points to be generated.
     * @return Array of points
     */
    private Point[] pointCloud(int amount) {
        Point[] points = new Point[amount];

        Random random;
        if (seed != 0) {
            random = new Random(seed);
        } else {
            random = new Random();
        }

        for (int i = 0; i < amount; i++) {
            int x = random.nextInt((width - 2) + 1) + 1;
            int y = random.nextInt((height - 2) + 1) + 1;

            this.data[x][y] = 1;
            points[i] = new Point(x, y);
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
                    color = new Color(0, 0, 0.5, 1);
                } else {
                    color = new Color(0.9, 0.9, 0.7, 1);
                }

                for (int k = 0; k < scale; k++) {
                    for (int l = 0; l < scale; l++) {
                        image.getPixelWriter().setColor(i * scale + k, j * scale + k, color);
                        image.getPixelWriter().setColor(i * scale + k, j * scale + l, color);
                    }
                }
            }
        }

        for (Triangle tri : triangles) {
            for (Point p : tri.points) {
                Color color = new Color(1, 0, 0.5, 1);
                for (int k = 0; k < scale; k++) {
                    for (int l = 0; l < scale; l++) {
                        if (p.x >= width || p.y >= height) {
                            continue;
                        }

                        image.getPixelWriter().setColor((int) p.x * scale + k, (int) p.y * scale + k, color);
                        image.getPixelWriter().setColor((int) p.x * scale + k, (int) p.y * scale + l, color);
                    }
                }
            }
        }

        return image;
    }

    public String mapToString() {
        return Arrays.deepToString(this.data);
    }
}
