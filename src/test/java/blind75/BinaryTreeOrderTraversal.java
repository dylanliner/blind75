package blind75;

import main.java.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BinaryTreeOrderTraversal {

    HashMap<Integer, List<Integer>> map = new HashMap<>();

    @Test
    public void test() {

        var root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        System.out.println(levelOrder(root).toString());
    }


    public List<List<Integer>> levelOrder(TreeNode root) {

        addToList(root, 0);
        int i = 1;
        var ans = new ArrayList<List<Integer>>();
        while (map.containsKey(i)) {
            ans.add(map.get(i));
            i++;
        }
        return ans;
    }

    public void addToList(TreeNode root, int depth) {

        if (root == null) {
            return;
        }
        depth++;
        map.computeIfAbsent(depth, x -> new ArrayList<>()).add(root.val);
        addToList(root.left, depth);
        addToList(root.right, depth);
    }
}
