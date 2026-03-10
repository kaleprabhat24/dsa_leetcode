class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;

        long[][] dp0 = new long[zero + 1][one + 1];
        long[][] dp1 = new long[zero + 1][one + 1];

        for (int i = 1; i <= Math.min(zero, limit); i++) dp0[i][0] = 1;
        for (int j = 1; j <= Math.min(one, limit); j++) dp1[0][j] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {

                long v0 = (dp0[i - 1][j] + dp1[i - 1][j]) % MOD;
                if (i - limit - 1 >= 0) {
                    v0 = (v0 - dp1[i - limit - 1][j] + MOD) % MOD;
                }
                dp0[i][j] = v0;

                long v1 = (dp0[i][j - 1] + dp1[i][j - 1]) % MOD;
                if (j - limit - 1 >= 0) {
                    v1 = (v1 - dp0[i][j - limit - 1] + MOD) % MOD;
                }
                dp1[i][j] = v1;
            }
        }

        return (int)((dp0[zero][one] + dp1[zero][one]) % MOD);
    }
}