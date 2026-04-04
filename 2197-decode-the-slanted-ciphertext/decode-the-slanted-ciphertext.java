class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;

        int n = encodedText.length();
        int cols = n / rows;

        char[][] grid = new char[rows][cols];

        int idx = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = encodedText.charAt(idx++);
            }
        }

        StringBuilder result = new StringBuilder();

        for (int start = 0; start < cols; start++) {
            int i = 0, j = start;

            while (i < rows && j < cols) {
                result.append(grid[i][j]);
                i++;
                j++;
            }
        }

        // remove trailing spaces
        int end = result.length() - 1;
        while (end >= 0 && result.charAt(end) == ' ') {
            end--;
        }

        return result.substring(0, end + 1);
    }
}