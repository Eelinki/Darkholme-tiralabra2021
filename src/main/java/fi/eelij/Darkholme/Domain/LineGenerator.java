package fi.eelij.Darkholme.Domain;

public class LineGenerator {
    private int[][] data;

    public LineGenerator(int[][] data) {
        this.data = data;
    }

    public void run(Edge edge) {
        int x0 = (int) edge.points[0].x;
        int x1 = (int) edge.points[1].x;
        int y0 = (int) edge.points[0].y;
        int y1 = (int) edge.points[1].y;

        int dx = Math.abs(x1 - x0);
        int sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0);
        int sy = y0 < y1 ? 1 : -1;
        int err = dx + dy;

        while (true) {
            int thickness = 2;

            for (int i = x0 - thickness; i < x0 + thickness; i++) {
                for (int j = y0 - thickness; j < y0 + thickness; j++) {
                    if (i < 0 || j < 0 || i > data.length - 1 || j > data[0].length - 1) {
                        continue;
                    }
                    this.data[i][j] = 1;
                }
            }

            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 >= dy) {
                err += dy;
                x0 += sx;
            }

            if (e2 <= dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
}
