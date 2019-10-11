package com.test.algorithm.bfsordfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/10/11
 */
public class BinaryTreePaths {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        List<String> arrayList = new ArrayList<>();
        public List<String> binaryTreePaths(TreeNode root) {

            if(root == null){
                return arrayList;
            }
            dfs(root,new ArrayList<>());
            return arrayList;
        }
        private void dfs(TreeNode root,List<Integer> list){
            if(root == null){
                return;
            }
            list.add(root.val);
            if(root.left == null && root.right == null){
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < list.size() - 1; i++) {
                    builder.append(list.get(i)+"->");
                }
                builder.append(root.val);
                arrayList.add(builder.toString());
            }
            if(root.left != null){
                dfs(root.left,list);
                list.remove(list.size() - 1);
            }
            if(root.right != null){
                dfs(root.right,list);
                list.remove(list.size() - 1);
            }
        }
    }

    static class Solution1 {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> arrayList = new ArrayList<>();
            if(root == null){
                return arrayList;
            }
            dfs(root,arrayList,"");
            return arrayList;
        }
        private void dfs(TreeNode root,List<String> list,String ans){
            if(root == null){
                return;
            }
            if(root.left == null && root.right == null){
                list.add(ans + root.val);
                return;
            }
            ans += root.val + "->";
            dfs(root.left,list,ans);
            dfs(root.right,list,ans);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = stringToTreeNode("[1,2,3,null,5]");
        System.out.println(new Solution1().binaryTreePaths(treeNode));
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
}
