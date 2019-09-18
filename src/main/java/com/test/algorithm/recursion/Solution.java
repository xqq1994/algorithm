package com.test.algorithm.recursion;


import java.util.ArrayList;
import java.util.List;

public class Solution {
    private TreeNode root;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> getNode(int num, int[] nums) {
        if (root == null) {
            root = new TreeNode(nums[0]);
        }
        TreeNode cur = root;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            searchTree(cur, nums[i], list);
        }
        return list;
    }

    public List<Integer> searchTree(TreeNode root, int val, List<Integer> res) {
        if (root != null) {
            if (val > root.val) {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    res.add(root.val);
                } else {
                    searchTree(root.right, val, res);
                }
            } else {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    res.add(root.val);
                } else {
                    searchTree(root.left, val, res);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getNode(5, new int[]{8, 9, 2, 3, 7,10,4}));
    }

}
