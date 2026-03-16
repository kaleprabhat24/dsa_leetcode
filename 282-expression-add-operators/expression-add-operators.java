class Solution {

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        backtrack(res, "", num, target, 0, 0, 0);
        return res;
    }

    private void backtrack(List<String> res, String path, String num, int target, int pos, long calc, long prev) {

        if (pos == num.length()) {
            if (calc == target) {
                res.add(path);
            }
            return;
        }

        for (int i = pos; i < num.length(); i++) {

            if (i != pos && num.charAt(pos) == '0') break;

            long cur = Long.parseLong(num.substring(pos, i + 1));

            if (pos == 0) {
                backtrack(res, path + cur, num, target, i + 1, cur, cur);
            } 
            else {
                backtrack(res, path + "+" + cur, num, target, i + 1, calc + cur, cur);

                backtrack(res, path + "-" + cur, num, target, i + 1, calc - cur, -cur);

                backtrack(res, path + "*" + cur, num, target, i + 1,
                          calc - prev + prev * cur, prev * cur);
            }
        }
    }
}