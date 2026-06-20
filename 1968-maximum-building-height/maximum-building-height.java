class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 0});
        for (int[] r : restrictions) {
            list.add(r);
        }
        // Also add the last building
        boolean hasLast = false;
        for (int[] r : restrictions) {
            if (r[0] == n) hasLast = true;
        }
        if (!hasLast) {
            list.add(new int[]{n, n - 1});
        }
        
        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));
        int m = list.size();
        
        // Left to right
        for (int i = 1; i < m; i++) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i - 1)[1] + dist);
        }
        
        // Right to left
        for (int i = m - 2; i >= 0; i--) {
            int dist = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(list.get(i)[1], list.get(i + 1)[1] + dist);
        }
        
        // Find maximum peak between any two adjacent restrictions
        int maxHeight = 0;
        for (int i = 0; i < m - 1; i++) {
            int idx1 = list.get(i)[0];
            int h1 = list.get(i)[1];
            int idx2 = list.get(i + 1)[0];
            int h2 = list.get(i + 1)[1];
            
            int dist = idx2 - idx1;
            int peak = (dist + h1 + h2) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }
        
        return maxHeight;
    }
}
