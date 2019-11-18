package com.test.algorithm.array;

import java.util.HashSet;

/**
 * TODO 947. 移除最多的同行或同列石头
 * 在二维平面上，我们将石头放置在一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 现在，move 操作将会移除与网格上的某一块石头共享一列或一行的一块石头。
 *
 * 我们最多能执行多少次 move 操作？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 示例 2：
 *
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 示例 3：
 *
 * 输入：stones = [[0,0]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class MostStonesRemovedWithSameRwOrColumn {
    static class Solution {
        private int[] pre = new int[20000];
        public int removeStones(int[][] stones) {
            int n = stones.length;
            if(n == 0) return 0;
            for (int i = 0; i < pre.length; i++) {
                pre[i] = i;
            }
            for (int i = 0; i < n; i++) {
                join(stones[i][0],stones[i][1]+10000);
            }
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(unionsearch(stones[i][0]));
            }
            return n - set.size();
        }

        public int unionsearch(int root){
            int son = root;
            while (root != pre[root]){
                root = pre[root];
            }
            while (son != pre[son]){
                int tmp = son;
                son = pre[son];
                pre[tmp] = root;
            }
            return root;
        }

        public void join(int root1,int root2){
            int x,y;
            x = unionsearch(root1);
            y = unionsearch(root2);
            if(x != y){
                pre[x] = y;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeStones(new int[][]{{0, 0},{0,1} ,{1,0}, {1, 2}, {2, 1}, {2, 2}}));
    }
}
