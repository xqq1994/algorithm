package com.test.algorithm.dichotomy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * TODO 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Sqrtx {
    static class Solution {
        //二分法
        public int mySqrt(int x) {
            if (x == 0 || x == 1) return x;
            int left = 0;
            int right = x;
            int res = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (mid == x / mid) {
                    return mid;
                } else if (mid < x / mid) {
                    left = mid + 1;
                    res = mid;
                } else {
                    right = mid - 1;
                }
            }
            return res;
        }
        //牛顿迭代法推荐使用
        public int mySqrt1(int x) {
            long r = x;
            while (r * r > x) {
                r = (r + x / r) / 2;
            }
            return (int)r;
        }

        //保留小数写法
        public double mySqrt(int x, int y) {
            if (x == 0 || x == 1) return x;
            double left = 0;
            double right = x;
            double res = 0;
            while (left <= right) {
                BigDecimal mid = new BigDecimal(left + right).divide(new BigDecimal("2"), y, BigDecimal.ROUND_HALF_UP);
                if (mid.compareTo(new BigDecimal(x).divide(mid, y, BigDecimal.ROUND_HALF_UP).setScale(y, BigDecimal.ROUND_HALF_UP)) == 0) {
                    return mid.doubleValue();
                } else if (mid.compareTo(new BigDecimal(x).divide(mid, y, BigDecimal.ROUND_HALF_UP).setScale(y, BigDecimal.ROUND_HALF_UP)) < 0) {
                    left = mid.doubleValue();
                    res = mid.doubleValue();
                } else {
                    right = mid.doubleValue();
                }
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String[] split = line.split(",");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

            int ret = new Solution().mySqrt1(x);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
