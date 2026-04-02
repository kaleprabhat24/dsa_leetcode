class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        
        int[][][] dp = new int[m][n][3];
        
        // initialize with very small values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        // start cell
        if (coins[0][0] >= 0) {
            dp[0][0][0] = coins[0][0];
        } else {
            dp[0][0][0] = coins[0][0];
            dp[0][0][1] = 0; // use neutralization
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= 2; k++) {
                    if (dp[i][j][k] == Integer.MIN_VALUE) continue;

                    // move right
                    if (j + 1 < n) {
                        int val = coins[i][j + 1];
                        if (val >= 0) {
                            dp[i][j + 1][k] = Math.max(dp[i][j + 1][k], dp[i][j][k] + val);
                        } else {
                            // take hit
                            dp[i][j + 1][k] = Math.max(dp[i][j + 1][k], dp[i][j][k] + val);
                            // neutralize
                            if (k < 2) {
                                dp[i][j + 1][k + 1] = Math.max(dp[i][j + 1][k + 1], dp[i][j][k]);
                            }
                        }
                    }

                    // move down
                    if (i + 1 < m) {
                        int val = coins[i + 1][j];
                        if (val >= 0) {
                            dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k] + val);
                        } else {
                            // take hit
                            dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k] + val);
                            // neutralize
                            if (k < 2) {
                                dp[i + 1][j][k + 1] = Math.max(dp[i + 1][j][k + 1], dp[i][j][k]);
                            }
                        }
                    }
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int k = 0; k <= 2; k++) {
            ans = Math.max(ans, dp[m - 1][n - 1][k]);
        }

        return ans;
    }
}