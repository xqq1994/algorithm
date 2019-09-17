package com.test.algorithm.map;

import java.util.HashMap;

/**
 * @Description: 149. 直线上最多的点数
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 * <p>
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/17
 */
public class MaxPointsOnALine {
    static class Solution {
        /*public int maxPoints(int[][] points) {
            int n = points.length;
            if (n <= 2) {
                return n;
            }
            int res = 0;
            for (int i = 0; i < n - 1; i++) {
                Map<String, Integer> slope = new HashMap<>();
                int repeat = 0;
                int tmpMax = 0;
                for (int j = i + 1; j < n; j++) {
                    int dy = points[i][1] - points[j][1];
                    int dx = points[i][0] - points[j][0];
                    if (dy == 0 && dx == 0) {
                        repeat++;
                        continue;
                    }
                    int g = gcd(dy, dx);
                    if (g != 0) {
                        dy /= g;
                        dx /= g;
                    }
                    String tmp = dy + "/" + dx;
                    slope.put(tmp, slope.getOrDefault(tmp, 0) + 1);
                    tmpMax = Math.max(tmpMax, slope.get(tmp));
                }
                res = Math.max(res, repeat + tmpMax + 1);
            }
            return res;
        }
        //约数法
        private int gcd(int dy, int dx) {
            if (dx == 0) {
                return dy;
            } else {
                return gcd(dx, dy % dx);
            }
        }*/
        public int maxPoints(int[][] points) {
            int n = points.length;
            if (n <= 2) {
                return n;
            }
            int res = 0;
            for (int i = 0; i < n; i++) {
                HashMap<String, Integer> map = new HashMap<>();
                int repeat = 0;
                int tmpMax = 0;
                for (int j = i + 1; j < n; j++) {
                    int dy = points[i][0] - points[j][0];
                    int dx = points[i][1] - points[j][1];
                    if (dy == 0 && dx == 0) {
                        repeat++;
                        continue;
                    }
                    int g = gcd(dy, dx);
                    if (g != 0) {
                        dy /= g;
                        dx /= g;
                    }
                    String tmp = dy + "/" + dx;
                    map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                    tmpMax = Math.max(tmpMax, map.get(tmp));
                }
                res = Math.max(res, repeat + tmpMax + 1);
            }
            return res;
        }

        //约数法
        private int gcd(int dy, int dx) {
            if (dx == 0) {
                return dy;
            }
            return gcd(dx, dy % dx);
        }
    }

    public static void main(String[] args) {
        int[][] ints = {{1, 1}, {2, 2}, {3, 3}};
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.println("a[" + i + "][" + j + "]," + ints[i][j]);
            }
        }
        System.out.println(new Solution().maxPoints(ints));
    }
}
