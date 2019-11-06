package com.test.algorithm.other;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 835. 图像重叠
 * 给出两个图像 A 和 B ，A 和 B 为大小相同的二维正方形矩阵。（并且为二进制矩阵，只包含0和1）。
 * <p>
 * 我们转换其中一个图像，向左，右，上，或下滑动任何数量的单位，并把它放在另一个图像的上面。之后，该转换的重叠是指两个图像都具有 1 的位置的数目。
 * <p>
 * （请注意，转换不包括向任何方向旋转。）
 * <p>
 * 最大可能的重叠是什么？
 * <p>
 * 示例 1:
 * <p>
 * 输入：A = [[1,1,0],
 * [0,1,0],
 *           [0,1,0]]
 *      B = [[0,0,0],
 *           [0,1,1],
 *           [0,0,1]]
 * 输出：3
 * 解释: 将 A 向右移动一个单位，然后向下移动一个单位。
 * 注意: 
 * <p>
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/image-overlap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/6
 */
public class ImageOverlap {
    static class Solution {
        public int largestOverlap(int[][] A, int[][] B) {
            ArrayList<Point> A1 = new ArrayList<>();
            ArrayList<Point> B1 = new ArrayList<>();
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    if (A[i][j] == 1) A1.add(new Point(i, j));
                    if (B[i][j] == 1) B1.add(new Point(i, j));
                }
            }
            HashSet<Point> bSet = new HashSet<>(B1);
            int ans = 0;
            Set<Point> seen = new HashSet<>();
            for (Point a : A1) {
                for (Point b : B1) {
                    Point delta = new Point(b.x - a.x, b.y - a.y);
                    if (!seen.contains(delta)) {
                        seen.add(delta);
                        int cand = 0;
                        for (Point p : A1) {
                            if (bSet.contains(new Point(p.x + delta.x, p.y + delta.y))) {
                                cand++;
                            }
                        }
                        ans = Math.max(cand, ans);
                    }
                }
            }
            return ans;
        }

        public int largestOverlap1(int[][] A, int[][] B) {
            int N = A.length;
            int[][] count = new int[2 * N + 1][2 * N + 1];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (A[i][j] == 1) {
                        for (int i2 = 0; i2 < N; ++i2) {
                            for (int j2 = 0; j2 < N; ++j2) {
                                if (B[i2][j2] == 1) {
                                    count[i - i2 + N][j - j2 + N] += 1;
                                }
                            }
                        }
                    }
                }
            }
            int ans = 0;
            for (int[] row : count) {
                for (int v : row) {
                    ans = Math.max(ans, v);
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestOverlap(new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 0, 1}}));
    }
}
