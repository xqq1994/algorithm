package com.test.algorithm.other;
/**
 * TODO 357. 计算各个位数不同的数字个数
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 *
 * 示例:
 *
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class CountNumbersWithUniqueDigits {
    static class Solution {
        public int countNumbersWithUniqueDigits(int n) {
            if(n == 0){
                return 1;
            }
            int[] dp = new int[11];
            dp[0] = 1;
            int per = 1;
            for (int i = 1; i < 11; i++) {
                dp[i] = dp[i - 1] + 9*per;
                per = per * (10 - i);
            }
            if(n <= 10){
                return dp[n];
            }else{
                return dp[10];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countNumbersWithUniqueDigits(2));

    }
}
