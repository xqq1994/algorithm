package com.test.algorithm.dp;
/**
 * TODO 931. 下降路径最小和
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 *
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 *
 *  
 *
 * 示例：
 *
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：12
 * 解释：
 * 可能的下降路径有：
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 和最小的下降路径是 [1,4,7]，所以答案是 12。
 *
 *  
 *
 * 提示：
 *
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class MinimumFallingPathSum {
    class Solution {
        public int minFallingPathSum(int[][] A) {
            int len = A.length;
            int[][] dp = new int[len + 1][len + 1];
            for (int i = 0; i < A.length; i++) {
                dp[0][i] = A[0][i];
            }
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    //第一行，只有两种情况，右边和本身
                    if (j == 0) {
                        //该位置的最小路径肯定是等于改点的值加上上一行同列以及上一行同列右边的最小路径
                        dp[i][j] = A[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                    } else if (j == len - 1) {
                        //该位置的最小路径肯定是等于该点的值加上上一行同列以及上一行同列左边的最小路径
                        dp[i][j] = A[i][j] + Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                    } else {
                        // 该位置的最小路径肯定是等于该点的值加上上一行同列以及上一行同列左右两边的最小路径
                        dp[i][j] = A[i][j] + Math.min(dp[i - 1][j + 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                    }
                }
            }
            //我么要求到达最有一行的最短路径，那么，先假设最短路径是最后一行的第一列
            int result = dp[len - 1][0];
            //然后遍历最有一行的每一列，取出最短路径
            for (int i = 0; i < len; i++) {
                result = Math.min(result, dp[len - 1][i]);
            }
            return result;
        }
    }
}
