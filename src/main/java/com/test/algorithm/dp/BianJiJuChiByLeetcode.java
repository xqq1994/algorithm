package com.test.algorithm.dp;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 72. 编辑距离
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * @Date 10:54 2019/7/30
 * @Param
 * @return
 */
public class BianJiJuChiByLeetcode {
    static class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            if (m * n == 0) {
                return m + n;
            }
            int[][] res = new int[m + 1][n + 1];
            for (int i = 0; i < m + 1; i++) {
                res[i][0] = i;
            }
            for (int j = 0; j < n + 1; j++) {
                res[0][j] = j;
            }
            //DP
            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    int left = res[i - 1][j] + 1;
                    int down = res[i][j - 1] + 1;
                    int leftDown = res[i - 1][j - 1];
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        leftDown += 1;
                    }
                    res[i][j] = Math.min(left, Math.min(down, leftDown));
                }
            }
            return res[m][n];
        }

        public int minDistance1(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            if (m * n == 0) {
                return m + n;
            }
            int[][] res = new int[m + 1][n + 1];
            for (int i = 0; i <= m; i++) {
                res[i][0] = i;
            }
            for (int j = 0; j <= n; j++) {
                res[0][j] = j;
            }
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                        res[i][j] = res[i - 1][j - 1];
                    }else{
                        res[i][j] = Math.min(res[i - 1][j - 1], Math.min(res[i - 1][j], res[i][j - 1])) + 1;
                    }

                }
            }
            return res[m][n];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.minDistance1("zoologicoarchaeologist", "zoogeologist");
    }
}