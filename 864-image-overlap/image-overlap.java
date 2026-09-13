import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();
        
        // Step 1: Collect coordinates of all 1s from both images
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) list1.add(new int[]{i, j});
                if (img2[i][j] == 1) list2.add(new int[]{i, j});
            }
        }
        
        // Step 2: Group translations by their vector transformations
        Map<String, Integer> counts = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : list1) {
            for (int[] p2 : list2) {
                // Calculate the specific shift vector
                int rowShift = p1[0] - p2[0];
                int colShift = p1[1] - p2[1];
                String key = rowShift + " " + colShift;
                
                // Increment frequency of this shift configuration
                counts.put(key, counts.getOrDefault(key, 0) + 1);
                maxOverlap = Math.max(maxOverlap, counts.get(key));
            }
        }
        
        return maxOverlap;
    }
}
