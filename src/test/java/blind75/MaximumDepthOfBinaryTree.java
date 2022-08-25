package blind75;

import amazon.TreeNode;
import org.junit.jupiter.api.Test;

public class MaximumDepthOfBinaryTree {

    @Test
    public void test() {
        var root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        System.out.println(maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        return computeMaxDepth(root, 0);
    }

    public int computeMaxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        return Math.max(computeMaxDepth(root.left, depth),
                computeMaxDepth(root.right, depth));
    }
}
