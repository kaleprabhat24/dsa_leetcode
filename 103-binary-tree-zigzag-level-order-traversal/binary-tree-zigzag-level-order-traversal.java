/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * Performs zigzag level order traversal of a binary tree.
     * Even levels (0-indexed) are traversed left to right,
     * Odd levels are traversed right to left.
     * 
     * @param root The root node of the binary tree
     * @return A list of lists containing node values at each level in zigzag order
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Initialize result list to store level-wise node values
        List<List<Integer>> result = new ArrayList<>();
      
        // Handle edge case: empty tree
        if (root == null) {
            return result;
        }
      
        // Use queue for level order traversal (BFS)
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
      
        // Flag to track traversal direction (true: left-to-right, false: right-to-left)
        boolean isLeftToRight = true;
      
        // Process nodes level by level
        while (!queue.isEmpty()) {
            // Store current level's node values
            List<Integer> currentLevel = new ArrayList<>();
          
            // Process all nodes at current level
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
              
                // Add left child to queue for next level processing
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
              
                // Add right child to queue for next level processing
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
          
            // Reverse the current level list if traversing right to left
            if (!isLeftToRight) {
                Collections.reverse(currentLevel);
            }
          
            // Add current level to result
            result.add(currentLevel);
          
            // Toggle direction for next level
            isLeftToRight = !isLeftToRight;
        }
      
        return result;
    }
}
