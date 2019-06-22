package com.test.algorithm.recursion;

/**
 * TODO 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PowxN {
    static class Solution {
        public double myPow(double x, int n) {
            double pow = 1.0;
            for (int i = n; i != 0; i /= 2) {
                if ((i & 1) == 1) {
                    pow *= x;
                }
                x *= x;
            }
            return n < 0 ? 1 / pow : pow;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            double v = solution.myPow(2, -2147483648);
            System.out.println(v);
        }
    }
}
