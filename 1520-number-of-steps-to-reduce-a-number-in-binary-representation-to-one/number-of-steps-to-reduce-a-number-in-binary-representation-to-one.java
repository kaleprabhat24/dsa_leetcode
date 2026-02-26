class Solution {
    public int numSteps(String s) {
        
        int steps = 0;
        int carry = 0;
        
        // start from rightmost bit (ignore first bit)
        for (int i = s.length() - 1; i > 0; i--) {
            
            int bit = s.charAt(i) - '0';
            
            if (bit + carry == 1) {
                // odd
                steps += 2;   // add 1 + divide by 2
                carry = 1;    // carry generated
            } else {
                // even
                steps += 1;   // divide by 2
                // carry stays same
            }
        }
        
        return steps + carry;  // final carry may add 1 step
    }
}