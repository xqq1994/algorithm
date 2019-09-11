package com.test.algorithm.tree;

import com.alibaba.fastjson.JSON;

/**
 * @Description: 654. 最大二叉树
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * <p>
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * <p>
 *  
 * <p>
 * 示例 ：
 * <p>
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 * <p>
 * 6
 * /   \
 * 3     5
 * \    /
 * 2  0
 * \
 * 1
 *  
 * <p>
 * 提示：
 * <p>
 * 给定的数组的大小在 [1, 1000] 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/11
 */
public class MaximumBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return reconstructMaximumBinaryTree(nums, 0, nums.length);
        }

        private TreeNode reconstructMaximumBinaryTree(int[] nums, int start, int end) {
            if(end - start < 1){
                return new TreeNode(nums[start]);
            }
            int max = start;
            for (int i = start; i < end; i++) {
                if(nums[i] > nums[max]){
                    max = i;
                }
            }
            TreeNode node = new TreeNode(nums[max]);
            //如果最大的数不是在最左边的位置
            if(max != start){
                node.left = reconstructMaximumBinaryTree(nums,start,max);
            }
            if(max != end - 1){
                node.right = reconstructMaximumBinaryTree(nums,max + 1,end);
            }
            return node;
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5})));
    }
}
