package amazon;

import blind75.KSmallestInBST;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllNodesDistanceKBinaryTree {

    List<Integer> arr = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        var map = new HashMap<Integer, List<Integer>>();
        //construct directed graph
        buildDirectedGraph(root, null, map);

        //from target, find all at two distance in graph
        var ans = new ArrayList<Integer>();
        calculateDistanceInAdjGraph(target.val, 0, k, map, ans);
        return ans;

    }

    public void buildDirectedGraph(TreeNode root, TreeNode parent, HashMap<Integer, List<Integer>> map) {
        var adj = new ArrayList<Integer>();
        if (parent != null) {
            adj.add(parent.val);
        }
        if (root.right != null) {
            adj.add(root.right.val);
            buildDirectedGraph(root.right, root, map);
        }
        if (root.left != null) {
            adj.add(root.left.val);
            buildDirectedGraph(root.left, root, map);
        }
        map.put(root.val, adj);
    }

    public void calculateDistanceInAdjGraph(int curVal, int curDistance, int targetDistance, HashMap<Integer, List<Integer>> map, ArrayList<Integer> ans) {
        if (!map.containsKey(curVal)) {
            return;
        }
        if (targetDistance == curDistance) {
            ans.add(curVal);
        }

        map.remove(curVal).forEach(adj -> {
            int finalCurDistance = curDistance + 1;
            calculateDistanceInAdjGraph(adj, finalCurDistance, targetDistance, map, ans);
        });
    }


    @Test
    public void test() {
        var node = new TreeNode(3);
        node.left = new TreeNode(5);
        node.left.right = new TreeNode(2);
        node.left.right.left = new TreeNode(7);
        node.left.right.right = new TreeNode(4);
        node.left.left = new TreeNode(6);
        node.right = new TreeNode(1);
        node.right.right = new TreeNode(8);
        node.right.left = new TreeNode(0);
        System.out.println(distanceK(node, node.left, 2));

    }
}
