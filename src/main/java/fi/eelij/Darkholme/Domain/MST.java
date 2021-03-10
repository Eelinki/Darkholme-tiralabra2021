package fi.eelij.Darkholme.Domain;

import fi.eelij.Darkholme.Util.CustomList;
import fi.eelij.Darkholme.Util.UniqueList;

public class MST {
    public Edge[] edges;
    public CustomList<Point> points;
    private UniqueList<Edge> mst;
    private CustomList<UniqueList<Point>> subsets;

    public MST(UniqueList<Edge> edges, CustomList<Point> points) {
        this.points = points;
        this.edges = new Edge[edges.size()];

        int index = 0;
        for (Edge e : edges) {
            this.edges[index] = e;
            index++;
        }

        this.mst = new UniqueList<>();
        this.subsets = new CustomList<>();
    }

    public UniqueList<Edge> getMST() {
        for (int i = 0; i < this.edges.length - 1; i++) {
            for (int j = 0; j < this.edges.length - i - 1; j++) {
                if (this.edges[j].compareTo(this.edges[j + 1]) > 0) {
                    Edge temp = this.edges[j];
                    this.edges[j] = this.edges[j + 1];
                    this.edges[j + 1] = temp;
                }
            }
        }

        for (Point p : points) {
            UniqueList<Point> set = new UniqueList<>();
            set.add(p);
            subsets.add(set);
        }

        for (Edge edge : edges) {
            Point p0 = edge.points[0];
            Point p1 = edge.points[1];

            int subsetA = 0, subsetB = 0;

            for (int j = 0; j < subsets.size(); j++) {
                if (subsets.get(j).contains(p0)) {
                    subsetA = j;
                }

                if (subsets.get(j).contains(p1)) {
                    subsetB = j;
                }
            }

            if (subsetA != subsetB) {
                mst.add(edge);

                union(subsetA, subsetB);
            }
        }

        return mst;
    }

    private void union(int subsetA, int subsetB) {
        UniqueList<Point> union = subsets.get(subsetA);
        for (Point p : subsets.get(subsetB)) {
            union.add(p);
        }

        if (subsetB > subsetA) {
            subsets.remove(subsetB);
            subsets.remove(subsetA);
        } else {
            subsets.remove(subsetA);
            subsets.remove(subsetB);
        }

        subsets.add(union);
    }
}
