package com.test.algorithm.bfsordfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TODO 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BinaryTreeRightSideView {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        //BFS
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return list;
            }
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = q.poll();
                    if(i == size-1){
                        list.add(poll.val);
                    }
                    if(poll.left != null) q.add(poll.left);
                    if(poll.right != null) q.add(poll.right);

                }
            }
            return list;
        }
        List<Integer> list = new ArrayList<>();
        //DFS
        public List<Integer> rightSideView1(TreeNode root) {
            dfs(root,1);
            return list;
        }
        public void dfs(TreeNode node,int i){
            if(node == null) return;
            if(list.size() < i) list.add(node.val);
            dfs(node.right,i+1);
            dfs(node.left,i+1);
        }
    }
}
