import java.util.*;

public class BellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of vertices:");
        int N = sc.nextInt();
        int[][] graph = new int[N][N];
        
        System.out.println("Enter the adjacency matrix (" + N + "x" + N + "):");
        System.out.println("(Use 0 for no edge between vertices)");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                graph[i][j] = sc.nextInt();
        
        System.out.println("Enter the source vertex (1-based index):");
        int source = sc.nextInt() - 1; // Convert 1-based to 0-based index
        
        // 1. Initialize distances
        int[] dist = new int[N];
        Arrays.fill(dist, 9999); // Use 9999 as "Infinity" for simplicity
        dist[source] = 0;

        // 2. Relax all edges (N - 1) times
        for (int i = 1; i < N; i++) {
            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    // If an edge exists and a shorter path is found
                    if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        // 3. Check for negative weight cycles
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Negative cycle detected");
                    return;
                }
            }
        }

        // 4. Output results
        System.out.println("\nShortest distances from source vertex " + (source + 1) + ":");
        for (int i = 0; i < N; i++) {
            System.out.println("Vertex " + (i + 1) + ": " + dist[i]);
        }
        
        sc.close();
    }
}