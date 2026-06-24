class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        if (n == 1) {
            return r - l + 1;
        }

        int k = r - l + 1;
        int size = 2 * k;

        long[][] M = new long[size][size];

        for (int i = 0; i != k; i++) {
            // up state matches down transitions below it
            for (int j = 0; j != i; j++) {
                M[i][k + j] = 1;
            }
            // down state matches up transitions above it
            for (int j = i + 1; j != k; j++) {
                M[k + i][j] = 1;
            }
        }

        long[][] M_pow = matrixPower(M, n - 1);

        long[] base = new long[size];
        for (int i = 0; i != size; i++) {
            base[i] = 1;
        }

        long totalCount = 0;
        for (int i = 0; i != size; i++) {
            long stateValue = 0;
            for (int j = 0; j != size; j++) {
                stateValue = (stateValue + M_pow[i][j] * base[j]) % MOD;
            }
            totalCount = (totalCount + stateValue) % MOD;
        }

        return (int) totalCount;
    }

    private long[][] matrixPower(long[][] matrix, int p) {
        int n = matrix.length;
        long[][] result = new long[n][n];
        for (int i = 0; i != n; i++) {
            result[i][i] = 1;
        }

        long[][] base = matrix;
        while (p != 0) {
            if ((p & 1) == 1) {
                result = multiplyMatrices(result, base);
            }
            base = multiplyMatrices(base, base);
            p >>>= 1;
        }
        return result;
    }

    private long[][] multiplyMatrices(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i != n; i++) {
            for (int k = 0; k != n; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j != n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }
}
