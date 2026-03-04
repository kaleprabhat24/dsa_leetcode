class Solution {
    public int numSpecial(int[][] mat) {
        
        int m = mat.length;        // number of rows
        int n = mat[0].length;     // number of columns
        
        int[] row = new int[m];    // to count 1s in each row
        int[] col = new int[n];    // to count 1s in each column
        
        // Step 1: Count 1s in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        
        int count = 0; // answer
        
        // Step 2: Check special positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && row[i] == 1 && col[j] == 1) {
                    count++;
                }
            }
        }
        
        return count;
    }
}