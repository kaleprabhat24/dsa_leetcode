class Solution {
    public int minFlips(String s) {
        int n = s.length();

        int cost1 = 0;
        int cost2 = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '1')
                    cost1++;
                else
                    cost2++;
            } else {
                if (s.charAt(i) == '0')
                    cost1++;
                else
                    cost2++;
            }
        }

        int result = Math.min(cost1, cost2);

        if (n % 2 == 1) {
            String doubled = s + s;

            for (int i = 1; i < n; i++) {
                char leaving = doubled.charAt(i - 1);
                char entering = doubled.charAt(i + n - 1);

                if ((i - 1) % 2 == 0) {
                    if (leaving == '1')
                        cost1--;
                    else
                        cost2--;
                } else {
                    if (leaving == '0')
                        cost1--;
                    else
                        cost2--;
                }

                if ((i + n - 1) % 2 == 0) {
                    if (entering == '1')
                        cost1++;
                    else
                        cost2++;
                } else {
                    if (entering == '0')
                        cost1++;
                    else
                        cost2++;
                }

                result = Math.min(result, Math.min(cost1, cost2));
            }
        }
        return result;
    }
}