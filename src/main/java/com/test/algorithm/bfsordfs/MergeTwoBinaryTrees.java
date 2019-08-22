package com.test.algorithm.bfsordfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 617. 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 9:01 2019/8/22
 * @Param
 * @return
 */
public class MergeTwoBinaryTrees {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        //不修改原二叉树
        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
            if(t1 == null){
                return t2;
            }
            if(t2 == null){
                return  t1;
            }
            TreeNode node = new TreeNode(t1.val + t2.val);
            node.left = mergeTrees(t1.left,t2.left);
            node.right = mergeTrees(t1.right,t2.right);
            return node;
        }
        //修改原二叉树
        public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
            if(t1 == null){
                return t2;
            }
            if(t2 == null){
                return  t1;
            }
            t1.val += t2.val;
            t1.left = mergeTrees1(t1.left,t2.left);
            t1.right = mergeTrees1(t1.right,t2.right);
            return t1;
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

        public static String treeNodeToString(TreeNode root) {
            if (root == null) {
                return "[]";
            }

            String output = "";
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while(!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.remove();

                if (node == null) {
                    output += "null, ";
                    continue;
                }

                output += String.valueOf(node.val) + ", ";
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            }
            return "[" + output.substring(0, output.length() - 2) + "]";
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                TreeNode t1 = stringToTreeNode(line);
                line = in.readLine();
                TreeNode t2 = stringToTreeNode(line);

                TreeNode ret = new Solution().mergeTrees(t1, t2);

                String out = treeNodeToString(ret);

                System.out.print(out);
            }
        }
    }

}
