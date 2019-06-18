package com.test.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ValidateBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //中序遍历法
    class Solution {
        List<Integer> integers = new ArrayList<>();

        public boolean isValidBST(TreeNode root) {
            List<Integer> integers1 = searchTree(root);
            for (int i = 1; i < integers1.size(); i++) {
                if (integers1.get(i) <= integers1.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }

        public List<Integer> searchTree(TreeNode tree) {
            if (tree != null) {
                searchTree(tree.left);
                integers.add(tree.val);
                searchTree(tree.right);
            }
            return integers;
        }
    }

    //递归法
    class Solution1 {

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isValidBST(root.left) && check(root.val) && isValidBST(root.right);
        }

        private boolean first = true;
        private int prev;

        public boolean check(int val) {
            if (first) {
                first = false;
                prev = val;
                return true;
            }
            if (val <= prev) {
                return false;
            }
            prev = val;
            return true;
        }
    }

    //递归大小判断法
    class Solution2 {

        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        private boolean isValidBST(TreeNode node, Integer min, Integer max) {
            if (node == null) {
                return true;
            }
            if (min != null && node.val <= min) {
                return false;
            }
            if (max != null && node.val >= max) {
                return false;
            }
            return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
        }

    }


}
