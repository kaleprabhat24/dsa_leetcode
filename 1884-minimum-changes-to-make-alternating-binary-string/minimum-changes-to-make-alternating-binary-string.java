class Solution {
    public int minOperations(String s) {
        int countStartWith0 = 0; // pattern: 010101...
        int countStartWith1 = 0; // pattern: 101010...
        
        for (int i = 0; i < s.length(); i++) {
            char expected0 = (i % 2 == 0) ? '0' : '1';
            char expected1 = (i % 2 == 0) ? '1' : '0';
            
            if (s.charAt(i) != expected0) countStartWith0++;
            if (s.charAt(i) != expected1) countStartWith1++;
        }
        
        return Math.min(countStartWith0, countStartWith1);
    }
}