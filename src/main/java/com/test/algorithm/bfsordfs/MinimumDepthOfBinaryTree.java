package com.test.algorithm.bfsordfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最小深度  2.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MinimumDepthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        //BFS最好解法
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int minDepth = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = q.poll();
                    if (poll.left == null && poll.right == null) return minDepth + 1;
                    if (poll.left != null) q.add(poll.left);
                    if (poll.right != null) q.add(poll.right);
                }
                minDepth++;
            }
            return minDepth;
        }

        //递归DFS 但是如果某颗树左右节点层级较大时，BFS较DFS高效
        public int minDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            } else {
                int left = minDepth(root.left);
                int right = minDepth(root.right);
                return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
            }
        }
    }
}
