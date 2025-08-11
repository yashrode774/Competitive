package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static List<List<int[]>> g;

    static int[] dijkstra(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        g = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            g.add(new ArrayList<>());
            dist[i] = (int) 1e9; // infinity
        }

        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            g.get(u).add(new int[]{v, wt});
            g.get(v).add(new int[]{u, wt}); // undirected graph
        }

        // Priority queue: (distance, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[src] = 0;
        pq.add(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int wtSoFar = node[0];
            int curr = node[1];

            for (int[] child : g.get(curr)) {
                if (dist[child[0]] > wtSoFar + child[1]) {
                    dist[child[0]] = wtSoFar + child[1];
                    pq.add(new int[]{dist[child[0]], child[0]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {
                {0, 1, 2},
                {0, 2, 4},
                {1, 2, 1},
                {1, 3, 7},
                {2, 4, 3},
                {3, 4, 1}
        };

        int src = 0;
        int[] dist = dijkstra(V, edges, src);

        System.out.println("Shortest distances from node " + src + ":");
        System.out.println(Arrays.toString(dist));
    }
}
