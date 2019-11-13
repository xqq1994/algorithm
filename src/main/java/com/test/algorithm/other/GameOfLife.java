package com.test.algorithm.other;


/**
 * @Description: 289. 生命游戏
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞具有一个初始状态 live（1）即为活细胞， 或 dead（0）即为死细胞。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * 输出:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * 进阶:
 * <p>
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/13
 */
public class GameOfLife {
    static class Solution {
        /**
         利用一个 two bits 的状态机来记录细胞状态, 第一位表示
         下一状态, 第二位表示当前状态:
         00: dead (next state) <- dead (current state)
         01: dead (next state) <- live (current state)
         10: live (next state) <- dead (current state)
         11: live (next state) <- live (current state)
         初始情况对应就是 00 和 01 (默认下一状态是 dead state)
         统计每个位置周围的 live 细胞数决定高位置 1 (live)还是
         0 (dead), 最后右移一位即为最终状态, 注意不需要考虑 01
         以及 00 的情况, 因为已经默认下一状态为 dead.
         **/
        public void gameOfLife(int[][] board) {
            if (board.length < 1) {
                return;
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    int liveCount = countLive(board, i, j);
                    if ((board[i][j] & 1) == 1) {
                        if (liveCount >= 2 && liveCount <= 3) {
                            board[i][j] = 3;
                        }
                    } else {
                        if (liveCount == 3) {
                            board[i][j] = 2;
                        }
                    }
                }
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] >>= 1;
                }
            }
        }

        public int countLive(int[][] b, int i, int j) {
            int count = 0;
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x < 0 || x > b.length - 1 || y < 0 || y > b[0].length - 1) {
                    continue;
                }
                count += b[x][y] & 1;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[][] res = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        new Solution().gameOfLife(res);
        for (int[] re : res) {
            for (int i : re) {
                System.out.print(i);
            }
            System.out.println();
        }

    }
}
