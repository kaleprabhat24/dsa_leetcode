import java.util.*;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        char[] word = new char[n + m - 1];
        Arrays.fill(word, '?');

        // Apply all 'T'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?' || word[i + j] == str2.charAt(j)) {
                        word[i + j] = str2.charAt(j);
                    } else {
                        return "";
                    }
                }
            }
        }

        // Fill remaining with 'a'
        for (int i = 0; i < word.length; i++) {
            if (word[i] == '?') word[i] = 'a';
        }

        // Enforce 'F'
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    boolean fixed = false;

                    for (int j = m - 1; j >= 0 && !fixed; j--) {
                        int pos = i + j;
                        char original = word[pos];

                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == original) continue;

                            word[pos] = c;

                            // Check all 'T' constraints still valid
                            boolean valid = true;
                            for (int t = 0; t < n && valid; t++) {
                                if (str1.charAt(t) == 'T') {
                                    for (int k = 0; k < m; k++) {
                                        if (word[t + k] != str2.charAt(k)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }
                            }

                            // Check this F is broken
                            boolean stillMatch = true;
                            for (int k = 0; k < m; k++) {
                                if (word[i + k] != str2.charAt(k)) {
                                    stillMatch = false;
                                    break;
                                }
                            }

                            if (valid && !stillMatch) {
                                fixed = true;
                                break;
                            }
                        }

                        if (!fixed) word[pos] = original;
                    }

                    if (!fixed) return "";
                }
            }
        }

        return new String(word);
    }
}