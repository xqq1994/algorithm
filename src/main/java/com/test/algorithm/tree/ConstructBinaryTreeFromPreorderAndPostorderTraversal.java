package com.test.algorithm.tree;

/**
 * @Description: 889. 根据前序和后序遍历构造二叉树
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 * <p>
 *  pre 和 post 遍历中的值是不同的正整数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/27
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        int preIndex = 0, postIndex = 0;

        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            TreeNode root = new TreeNode(pre[preIndex++]);
            if (root.val != post[postIndex]) {
                root.left = constructFromPrePost(pre, post);
            }
            if (root.val != post[postIndex]) {
                root.right = constructFromPrePost(pre, post);
            }
            postIndex++;
            return root;
        }

        public void system(TreeNode root) {
            if (root != null) {
                System.out.println(root.val);
                system(root.left);
                system(root.right);
            }
        }

        public TreeNode constructFromPrePost1(int[] pre, int[] post) {
            return reConstructBinaryTree(pre, 0, pre.length - 1, post, 0, post.length - 1);
        }

        private TreeNode reConstructBinaryTree(int[] pre, int preStar, int preEnd, int[] post, int postStar, int postEnd) {
            if (preStar > preEnd || postStar > postEnd) {
                return null;
            }
            TreeNode root = new TreeNode(pre[preStar]);
            for (int i = postStar; i <= postEnd; i++) {
                if (preStar + 1 <= preEnd && pre[preStar + 1] == post[i]) {
                    root.left = reConstructBinaryTree(pre, preStar + 1, preStar + (i - postStar) + 1, post, postStar, i);
                    root.right = reConstructBinaryTree(pre, preStar + (i - postStar + 1) + 1, preEnd, post, i + 1, preEnd - 1);
                    break;
                }
            }
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new Solution().constructFromPrePost1(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        new Solution().system(treeNode);
    }
}
