import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row - col + n
        boolean[] diag2 = new boolean[2 * n]; // row + col
        
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        backtrack(0, n, board, result, cols, diag1, diag2);
        return result;
    }
    
    private void backtrack(int row, int n, char[][] board,
                           List<List<String>> result,
                           boolean[] cols, boolean[] diag1, boolean[] diag2) {
        
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(board[i]));
            }
            result.add(temp);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;
            
            if (cols[col] || diag1[d1] || diag2[d2]) continue;
            
            board[row][col] = 'Q';
            cols[col] = diag1[d1] = diag2[d2] = true;
            
            backtrack(row + 1, n, board, result, cols, diag1, diag2);
            
            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }
}