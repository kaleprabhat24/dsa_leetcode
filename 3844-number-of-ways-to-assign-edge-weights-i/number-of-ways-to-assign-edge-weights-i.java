import java.util.ArrayList;
import java.util.List;

class Solution {
    public int assignEdgeWeights(int[][] edges) {
        // Since it's a tree, number of nodes n = edges.length + 1
        int n = edges.length + 1;
        
        // Step 1: Build the tree structure using an adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        // Step 2: Use DFS to find the maximum depth from root node 1
        int maxDepth = dfs(1, 0, 0, adj);
        
        // Step 3: Compute 2^(maxDepth - 1) % (10^9 + 7)
        long MOD = 1_000_000_007;
        long result = 1;
        
        for (int i = 0; i < maxDepth - 1; i++) {
            result = (result * 2) % MOD;
        }
        
        return (int) result;
    }
    
    private int dfs(int node, int parent, int depth, List<List<Integer>> adj) {
        int maxDepthReached = depth;
        
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                maxDepthReached = Math.max(maxDepthReached, dfs(neighbor, node, depth + 1, adj));
            }
        }
        
        return maxDepthReached;
    }
}
