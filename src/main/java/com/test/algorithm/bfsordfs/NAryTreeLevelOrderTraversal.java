package com.test.algorithm.bfsordfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * <p>
 * 例如，给定一个 3叉树 :
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 *  
 * <p>
 * 返回其层序遍历:
 * <p>
 * [
 * [1],
 * [3,2,4],
 * [5,6]
 * ]
 *  
 * <p>
 * 说明:
 * <p>
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/5
 */
public class NAryTreeLevelOrderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Solution {
        //bfs
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> list = new ArrayList<>();
            if(root == null){
                return list;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                int size = queue.size();
                List<Integer> arrayList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Node poll = queue.poll();
                    arrayList.add(poll.val);
                    List<Node> children = poll.children;
                    if(!children.isEmpty()){
                        for (Node child : children) {
                            queue.add(child);
                        }
                    }
                }
                list.add(arrayList);
            }
            return list;
        }
        //dfs
        public List<List<Integer>> levelOrder1(Node root) {
            List<List<Integer>> list = new ArrayList<>();
            if(root ==null) return list;
            dfs(root,0,list);
            return list;
        }
        public void dfs(Node root,int depth,List<List<Integer>> list){
            if(root == null) return;
            if(depth + 1 > list.size()){
                list.add(new ArrayList<>());
            }
            list.get(depth).add(root.val);
            for (Node child : root.children) {
                if(child != null){
                    dfs(child,depth+1,list);
                }
            }
        }
    }

}
