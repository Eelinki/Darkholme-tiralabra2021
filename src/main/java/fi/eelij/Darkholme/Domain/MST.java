package fi.eelij.Darkholme.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MST {
    public ArrayList<Edge> edges;
    public ArrayList<Point> points;
    private HashSet<Edge> mst;
    private ArrayList<HashSet<Point>> subsets;

    public MST(HashSet<Edge> edges, ArrayList<Point> points) {
        this.points = points;
        this.edges = new ArrayList<>(edges);
        this.mst = new HashSet<>();
        this.subsets = new ArrayList<>();
    }

    public HashSet<Edge> getMST() {
        Collections.sort(this.edges);

        for (Point p : points) {
            HashSet<Point> set = new HashSet<>();
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
        HashSet<Point> union = subsets.get(subsetA);
        union.addAll(subsets.get(subsetB));

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
