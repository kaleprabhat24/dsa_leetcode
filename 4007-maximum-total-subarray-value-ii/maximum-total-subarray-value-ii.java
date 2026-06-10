import java.util.PriorityQueue;

public class Solution {
    
    // Custom class to store the subarray state in the Priority Queue
    private static class SubarrayState implements Comparable<SubarrayState> {
        long value;
        int l;
        int r;

        public SubarrayState(long value, int l, int r) {
            this.value = value;
            this.l = l;
            this.r = r;
        }

        // Sort in descending order to act as a Max-Heap
        @Override
        public int compareTo(SubarrayState o) {
            return Long.compare(o.value, this.value);
        }
    }

    // RENAMED METHOD: Matches LeetCode's internal driver method identifier exactly
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) {
            return 0;
        }

        // 1. Precompute log values for O(1) Sparse Table lookups
        int[] logTable = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            logTable[i] = logTable[i >> 1] + 1;
        }
        int maxLog = logTable[n] + 1;

        // 2. Build Sparse Tables for Min and Max Range Queries
        int[][] stMax = new int[n][maxLog];
        int[][] stMin = new int[n][maxLog];

        for (int i = 0; i < n; i++) {
            stMax[i][0] = nums[i];
            stMin[i][0] = nums[i];
        }

        for (int j = 1; j < maxLog; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[i][j] = Math.max(stMax[i][j - 1], stMax[i + (1 << (j - 1))][j - 1]);
                stMin[i][j] = Math.min(stMin[i][j - 1], stMin[i + (1 << (j - 1))][j - 1]);
            }
        }
        
        // 3. Initialize Max-Heap with the maximum range for each starting index 'l' (which is at r = n - 1)
        PriorityQueue<SubarrayState> maxHeap = new PriorityQueue<>();
        for (int l = 0; l < n; l++) {
            long val = getRangeValue(l, n - 1, logTable, stMax, stMin);
            maxHeap.add(new SubarrayState(val, l, n - 1));
        }

        // 4. Greedily extract top k elements
        long totalValue = 0;
        for (int step = 0; step < k; step++) {
            if (maxHeap.isEmpty()) break;
            
            SubarrayState curr = maxHeap.poll();
            totalValue += curr.value;

            // If the subarray length is greater than 1, push the next best alternative (l, r - 1)
            if (curr.r > curr.l) {
                int nextR = curr.r - 1;
                long nextVal = getRangeValue(curr.l, nextR, logTable, stMax, stMin);
                maxHeap.add(new SubarrayState(nextVal, curr.l, nextR));
            }
        }

        return totalValue;
    }

    // Helper method to compute max(nums[l..r]) - min(nums[l..r]) in O(1) time
    private long getRangeValue(int l, int r, int[] logTable, int[][] stMax, int[][] stMin) {
        int len = r - l + 1;
        int j = logTable[len];
        int mx = Math.max(stMax[l][j], stMax[r - (1 << j) + 1][j]);
        int mn = Math.min(stMin[l][j], stMin[r - (1 << j) + 1][j]);
        return (long) mx - mn;
    }
}
