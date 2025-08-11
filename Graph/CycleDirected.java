package org.example;

import java.util.*;

public class Main {
    static List<List<Integer>> g;
    static boolean[] vis;
    static boolean[] pathVis;

    static boolean dfs(int node) {
        vis[node] = true;
        pathVis[node] = true;

        for (int child : g.get(node)) {
            if (!vis[child]) {
                if (dfs(child)) return true; // cycle found
            } else if (pathVis[child]) {
                return true; // back edge → cycle
            }
        }
        pathVis[node] = false; // remove from recursion stack
        return false;
    }

    static void addEdge(int u, int v) {
        // Directed edge: u → v
        g.get(u).add(v);
    }

    public static void main(String[] args) {
        int courses = 4;
        g = new ArrayList<>();
        for (int i = 0; i < courses; i++) {
            g.add(new ArrayList<>());
        }
        vis = new boolean[courses];
        pathVis = new boolean[courses];
      
        g = new ArrayList<>();
        for (int i = 0; i < courses; i++) {
            g.add(new ArrayList<>());
        }
        vis = new boolean[courses];
        pathVis = new boolean[courses];

        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 1); // introduces a cycle

        boolean cycle = false;
        for (int i = 0; i < courses; i++) {
            if (!vis[i]) {
                if (dfs(i)) {
                    cycle = true;
                    break;
                }
            }
        }
        System.out.println(cycle ? "Cycle Detected" : "No Cycle");
    }
}

// 0 - 1
// |   |
// 3 - 2

// 1 - 2 - 3
