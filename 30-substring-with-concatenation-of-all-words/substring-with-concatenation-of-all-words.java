import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || s.length() == 0 || words.length == 0)
            return result;
        
        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        
        // Store frequency of words
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }
        
        // Try each possible starting offset
        for (int i = 0; i < wordLen; i++) {
            
            int left = i;
            int count = 0;
            Map<String, Integer> seenMap = new HashMap<>();
            
            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                
                String currentWord = s.substring(j, j + wordLen);
                
                if (wordMap.containsKey(currentWord)) {
                    
                    seenMap.put(currentWord, 
                        seenMap.getOrDefault(currentWord, 0) + 1);
                    
                    count++;
                    
                    // If word appears more than required
                    while (seenMap.get(currentWord) > wordMap.get(currentWord)) {
                        
                        String leftWord = s.substring(left, left + wordLen);
                        seenMap.put(leftWord, seenMap.get(leftWord) - 1);
                        
                        left += wordLen;
                        count--;
                    }
                    
                    // If we matched all words
                    if (count == wordCount) {
                        result.add(left);
                    }
                    
                } else {
                    // Reset window
                    seenMap.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }
        
        return result;
    }
}