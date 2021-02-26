package fi.eelij.Darkholme.Domain;

import fi.eelij.Darkholme.Util.CustomList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public class MST {
    public ArrayList<Edge> edges;
    public CustomList<Point> points;
    private LinkedHashSet<Edge> mst;
    private CustomList<LinkedHashSet<Point>> subsets;

    public MST(LinkedHashSet<Edge> edges, CustomList<Point> points) {
        this.points = points;
        this.edges = new ArrayList<>();

        for (Edge e : edges) {
            this.edges.add(e);
        }

        this.mst = new LinkedHashSet<>();
        this.subsets = new CustomList<>();
    }

    public LinkedHashSet<Edge> getMST() {
        Collections.sort(this.edges);

        for (Point p : points) {
            LinkedHashSet<Point> set = new LinkedHashSet<>();
            set.add(p);
            subsets.add(set);
        }

        for (int i = 0; i < edges.size(); i++) {
            Point p0 = edges.get(i).points[0];
            Point p1 = edges.get(i).points[1];

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
                mst.add(edges.get(i));

                union(subsetA, subsetB);
            }
        }

        return mst;
    }

    private void union(int subsetA, int subsetB) {
        LinkedHashSet<Point> union = subsets.get(subsetA);
        union.addAll(subsets.get(subsetB));

        if (subsetB > subsetA) {
            subsets.remove(subsets.get(subsetB));
            subsets.remove(subsets.get(subsetA));
        } else {
            subsets.remove(subsets.get(subsetA));
            subsets.remove(subsets.get(subsetB));
        }

        subsets.add(union);
    }
}
