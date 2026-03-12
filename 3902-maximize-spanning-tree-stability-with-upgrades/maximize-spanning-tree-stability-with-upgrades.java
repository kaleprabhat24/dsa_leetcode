class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;
            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pb] < rank[pa]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int lo = 0, hi = 2000000000;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (can(n, edges, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else hi = mid - 1;
        }

        return ans;
    }

    boolean can(int n, int[][] edges, int k, int target) {
        DSU dsu = new DSU(n);
        int used = 0;

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < target) return false;
                if (!dsu.union(e[0], e[1])) return false;
                used++;
            }
        }

        List<int[]> list = new ArrayList<>();
        for (int[] e : edges) if (e[3] == 0) list.add(e);

        list.sort((a, b) -> b[2] - a[2]);

        int upgrades = 0;

        for (int[] e : list) {
            if (used == n - 1) break;

            int u = e[0], v = e[1], s = e[2];

            if (dsu.find(u) == dsu.find(v)) continue;

            if (s >= target) {
                dsu.union(u, v);
                used++;
            } else if (s * 2 >= target && upgrades < k) {
                dsu.union(u, v);
                upgrades++;
                used++;
            }
        }

        return used == n - 1;
    }
}