class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                set.add(grid[r][c]); // size 0 rhombus

                int maxSize = Math.min(Math.min(r, m - 1 - r), Math.min(c, n - 1 - c));

                for (int k = 1; k <= maxSize; k++) {
                    int sum = 0;

                    int i = r - k, j = c;

                    for (int t = 0; t < k; t++) sum += grid[i + t][j + t];
                    for (int t = 0; t < k; t++) sum += grid[r + t][c + k - t];
                    for (int t = 0; t < k; t++) sum += grid[r + k - t][c - t];
                    for (int t = 0; t < k; t++) sum += grid[r - t][c - k + t];

                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] res = new int[size];
        int i = 0;

        for (int val : set) {
            if (i == size) break;
            res[i++] = val;
        }

        return res;
    }
}