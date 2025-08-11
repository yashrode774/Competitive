package org.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Main {
    static List<List<Integer>> g;
    static boolean[] vis;

    static boolean dfs(int node, int parent) {
        vis[node] = true;
        System.out.print(node + " ");
        for (int child: g.get(node)) {
            if (!vis[child]) {
                if (dfs(child, node)) return true;
            }
            else if (child != parent) return true;
        }
        return false;
    }

    static void addEdge(int u, int v) {
        g.get(u).add(v);
        g.get(v).add(u);
    }

    public static void main(String[] args) {
        g = new ArrayList<>();
        int v = 4;
        for (int i = 0; i < v; i++) {
            g.add(new ArrayList<>());
        }
        vis = new boolean[v];

        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 0);

        String ans = dfs(0, -1) ? "Cycle" : "No Cyclce";
        System.out.println(ans);
    }
}

// 0 - 1
// |   |
// 3 - 2

// 1 - 2 - 3
