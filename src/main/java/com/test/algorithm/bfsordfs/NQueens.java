package com.test.algorithm.bfsordfs;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 51. N皇后(困难)
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class NQueens {


    static class Solution {
        int rows[];
        // "hill" diagonals
        int hills[];
        // "dale" diagonals
        int dales[];
        int n;
        // output
        List<List<String>> output = new ArrayList();
        // queens positions
        int queens[];

        public boolean isNotUnderAttack(int row, int col) {
            int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
            return (res == 0) ? true : false;
        }

        public void placeQueen(int row, int col) {
            queens[row] = col;
            rows[col] = 1;
            hills[row - col + 2 * n] = 1;  // "hill" diagonals
            dales[row + col] = 1;   //"dale" diagonals
        }

        public void removeQueen(int row, int col) {
            queens[row] = 0;
            rows[col] = 0;
            hills[row - col + 2 * n] = 0;
            dales[row + col] = 0;
        }

        public void addSolution() {
            List<String> solution = new ArrayList<String>();
            for (int i = 0; i < n; ++i) {
                int col = queens[i];
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < col; ++j) sb.append(".");
                sb.append("Q");
                for (int j = 0; j < n - col - 1; ++j) sb.append(".");
                solution.add(sb.toString());
            }
            output.add(solution);
        }

        public void backtrack(int row) {
            for (int col = 0; col < n; col++) {
                if (isNotUnderAttack(row, col)) {
                    placeQueen(row, col);
                    // if n queens are already placed
                    if (row + 1 == n) addSolution();
                        // if not proceed to place the rest
                    else backtrack(row + 1);
                    // backtrack
                    removeQueen(row, col);
                }
            }
        }

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            rows = new int[n];
            hills = new int[4 * n - 1];
            dales = new int[2 * n - 1];
            queens = new int[n];

            backtrack(0);
            return output;
        }

        //太难未解出
        public static void main(String[] args) {
            Solution solution = new Solution();
            List<List<String>> lists = solution.solveNQueens(4);
            for (List<String> list : lists) {
                System.out.println(JSON.toJSONString(list));
            }
        }
    }
}