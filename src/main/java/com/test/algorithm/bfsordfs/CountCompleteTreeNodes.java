package com.test.algorithm.bfsordfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * 输出: 6
 * @Date 10:51 2019/8/14
 * @Param
 * @return
 */
public class CountCompleteTreeNodes {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        //BFS广度优先
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int a = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = q.poll();
                    if(poll.left != null) {
                        q.add(poll.left);
                    }
                    if(poll.right != null) {
                        q.add(poll.right);
                    }
                    a++;
                }
            }
            return a;
        }
        private int res;
        //DFS深度优先
        public int countNodes1(TreeNode root) {
            res = 0;
            dfs(root);
            return res;
        }
        public void dfs(TreeNode node){
            if(node == null) {
                return;
            }
            res++;
            dfs(node.left);
            dfs(node.right);
        }
        private int getLeftmostPathHeight(TreeNode root) {
            int h = 0;
            while (root != null) {
                root = root.left;
                h += 1;
            }
            return h;
        }
        //O((logn)^2)
        public int countNodes2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int ans = 0;
            TreeNode curr = root;
            while (curr != null) {
                int l = getLeftmostPathHeight(curr.left);
                int r = getLeftmostPathHeight(curr.right);

                // in case left and right subtree have same height, go right subtree
                // because left subtree will be full
                if (l == r) {
                    ans += 1 << l;
                    curr = curr.right;
                } else { // in case left subtree is higher, go left subtree
                    // because right subtree will be full
                    ans += 1 << r;
                    curr = curr.left;
                }
            }
            return ans;
        }
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);

            int ret = new Solution().countNodes2(root);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

}
