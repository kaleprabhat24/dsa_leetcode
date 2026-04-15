class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = Integer.MAX_VALUE;
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                found = true;

                int diff = Math.abs(i - startIndex);
                int circularDist = Math.min(diff, n - diff);

                ans = Math.min(ans, circularDist);
            }
        }

        return found ? ans : -1;
    }
}