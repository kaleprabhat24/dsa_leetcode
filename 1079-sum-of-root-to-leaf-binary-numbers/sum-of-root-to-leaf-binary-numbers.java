class Solution {
    
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode node, int current) {
        
        if (node == null) return 0;
        
        // Build binary number
        current = (current << 1) + node.val;
        
        // If leaf node, return the number
        if (node.left == null && node.right == null) {
            return current;
        }
        
        // Return sum of left and right subtree
        return dfs(node.left, current) + dfs(node.right, current);
    }
}