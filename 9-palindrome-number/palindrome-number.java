class Solution {
    public boolean isPalindrome(int x) {
        int num = 0;
        int sum = x;

        while(x>0){
            int ld = x%10;
            num = (num*10) + ld;
            x = x/10;
        }
        return num == sum;
    }
}