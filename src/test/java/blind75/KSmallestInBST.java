package blind75;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KSmallestInBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int kthSmallest(TreeNode root, int k) {
        var arr = new ArrayList<Integer>();
        treeTraversal(arr, root);
        arr.sort(Comparator.naturalOrder());
        return arr.get(k - 1);
    }

    public void treeTraversal(List<Integer> arr, TreeNode root) {

        if (root == null) {
            return;
        }
        arr.add(root.val);
        treeTraversal(arr, root.left);
        treeTraversal(arr, root.right);

    }

    @Test
    public void test() {
        var node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.left.right = new TreeNode(2);
        node.right = new TreeNode(4);

        System.out.println(kthSmallest(node, 1));
    }
}
