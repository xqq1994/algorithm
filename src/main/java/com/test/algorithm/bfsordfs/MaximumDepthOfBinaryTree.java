package com.test.algorithm.bfsordfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MaximumDepthOfBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    class Solution {
        //BFS效率会很低
        public int maxDepth(TreeNode root) {
            if(root == null) return 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int maxDepth = 0;
            while (!q.isEmpty()){
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = q.poll();
                    if(poll.left != null) q.add(poll.left);
                    if(poll.right != null) q.add(poll.right);
                }
                maxDepth++;
            }
            return maxDepth;
        }
        //DFS 写法更加高效 1行代码
        public int maxDepth1(TreeNode root) {
            return root == null ? 0 : 1 + Math.max(maxDepth1(root.left),maxDepth1(root.right));
        }
    }
}
