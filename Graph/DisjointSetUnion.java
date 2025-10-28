package org.example;

import java.util.*;

class DisjointSet {
    int[] size, parent;
    public DisjointSet(int n) {
        size = new int[n + 1];
        parent = new int[n + 1];
        Arrays.fill(size, 1);
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    int findUltimateParent(int node) {
        if (parent[node] == node) return node;
        // parent[node] = this step is the path compression as we are storing the ultimate parent while BackTracking.
        return parent[node] = findUltimateParent(parent[node]);
    }

    void unionBySize(int u, int v) {
        int ulp_u = findUltimateParent(u);
        int ulp_v = findUltimateParent(v);
        if (ulp_u == ulp_v) return;
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

public class Main {

    public static void main(String[] args) {
        DisjointSet dsu = new DisjointSet(7); // Create 7 elements (0 to 6)

        // Perform some unions
        dsu.unionBySize(0, 1);
        dsu.unionBySize(1, 2);
        dsu.unionBySize(3, 4);
        dsu.unionBySize(5, 6);
        dsu.unionBySize(4, 5);

        // Check connectivity
        System.out.println("Find(0) == Find(2)? " + (dsu.findUltimateParent(0) == dsu.findUltimateParent(2))); // true
        System.out.println("Find(0) == Find(3)? " + (dsu.findUltimateParent(0) == dsu.findUltimateParent(3))); // false
        System.out.println("Find(3) == Find(6)? " + (dsu.findUltimateParent(3) == dsu.findUltimateParent(6))); // true

        // Try connecting two sets
        dsu.unionBySize(2, 3);
        System.out.println("After union(2,3), Find(0) == Find(6)? " + (dsu.findUltimateParent(0) == dsu.findUltimateParent(6))); // true
    }
}
