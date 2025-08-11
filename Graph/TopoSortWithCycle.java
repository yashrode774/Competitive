package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    static List<List<Integer>> g;
    static boolean[] vis;
    static boolean[] pathVis;
    static Stack<Integer> st;

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
        st.add(node); // add to stack for topo order
        pathVis[node] = false;
        return false;
    }

    static void addEdge(int u, int v) {
        // Directed edge: u → v
        g.get(u).add(v);
    }

    public static void main(String[] args) {
        int v = 4;
        g = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            g.add(new ArrayList<>());
        }
        vis = new boolean[v];
        pathVis = new boolean[v];
        st = new Stack<>();

        // Example edges (prerequisite → course)
        addEdge(1, 0);
        addEdge(2, 0);
        addEdge(3, 1);
        addEdge(3, 2);

        boolean cycle = false;
        for (int i = 0; i < v; i++) {
            if (!vis[i]) {
                if (dfs(i)) {
                    cycle = true;
                    break;
                }
            }
        }

        if (cycle) {
            System.out.println("Cycle detected — no valid ordering.");
        } else {
            int[] ans = new int[st.size()];
            int i = 0;
            while (!st.isEmpty()) {
                ans[i++] = st.pop();
            }
            System.out.println("Topological Order: " + Arrays.toString(ans));
        }
    }
}
