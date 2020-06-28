package com.test.algorithm.bfsordfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 * 注意：
 * <p>
 * 节点值的范围在32位有符号整数范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/23
 */
public class AverageOfLevelsInBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> list = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                double sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    sum += poll.val;
                    if(poll.left != null){
                        queue.add(poll.left);
                    }
                    if(poll.right != null){
                        queue.add(poll.right);

//
                    }
                }
                list.add(sum / size);
            }
            return list;
        }
    }

}
