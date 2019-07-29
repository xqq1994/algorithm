package com.test.algorithm.dp;
/**
 * @Author xiaoqiangqiang
 * @Description //TODO 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 * @Date 9:34 2019/7/29
 * @Param 
 * @return
 */
public class MinimumPathSum {
    class Solution {
        public int minPathSum(int[][] grid) {
            if(grid==null){
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;
            int[][] res = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(i==0){
                        res[i][j] = j==0?grid[0][0]:res[i][j-1]+grid[i][j];
                    }else if(j == 0){
                        res[i][j] = i==0?grid[0][0]:res[i-1][j]+grid[i][j];
                    }else{
                        res[i][j]= Math.min(res[i][j-1],res[i-1][j])+grid[i][j];
                    }
                }
            }
            return res[m-1][n-1];
        }

    }
}
