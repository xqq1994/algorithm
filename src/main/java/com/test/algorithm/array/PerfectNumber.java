package com.test.algorithm.array;

/**
 * TODO 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 * <p>
 * 给定一个 正整数 n， 如果他是完美数，返回 True，否则返回 False
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入: 28
 * 输出: True
 * 解释: 28 = 1 + 2 + 4 + 7 + 14
 *  
 * <p>
 * 注意:
 * <p>
 * 输入的数字 n 不会超过 100,000,000. (1e8)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PerfectNumber {
    static class Solution {
        public boolean checkPerfectNumber(int num) {
            if (num == 1) {
                return false;
            }
            int sum = 0;
            int i = 2;
            for (; i < Math.sqrt(num); i++) {
                if (num % i == 0) {
                    sum += i;
                    sum += num / i;
                }
            }
            if (i * i == num) {
                sum += i;
            }
            sum += 1;
            return sum == num;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        for (int i = 0; i < 100; i++) {
        boolean b = solution.checkPerfectNumber(28);
        System.out.println(/*i+""+*/b);
//        }
    }

}
