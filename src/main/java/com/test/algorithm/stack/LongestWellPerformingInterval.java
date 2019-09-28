package com.test.algorithm.stack;

import java.util.Stack;

/**
 * TODO 1124. 表现良好的最长时间段
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * <p>
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * <p>
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * <p>
 * 请你返回「表现良好时间段」的最大长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：hours = [9,9,6,0,6,6,9]
 * 输出：3
 * 解释：最长的表现良好时间段是 [9,9,6]。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= hours.length <= 10000
 * 0 <= hours[i] <= 16
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-well-performing-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class LongestWellPerformingInterval {
    static class Solution {
        public int longestWPI(int[] hours) {
            int max = 0;
            int[] presum = new int[hours.length + 1];
            for (int i = 0; i < hours.length; i++) {
                if (hours[i] > 8) {
                    hours[i] = 1;
                    max = 1;
                } else {
                    hours[i] = -1;
                }
                presum[0] = 0;
                presum[i + 1] = presum[i] + hours[i];
            }
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < presum.length - 1; i++) {
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    if (presum[stack.peek()] > presum[i]) {
                        stack.push(i);
                    }
                }
            }
            for (int i = presum.length - 1; i >= 0; i--) {
                int last = i;
                while (!stack.isEmpty() && presum[i] > presum[stack.peek()]) {
                    last = stack.pop();
                }
                if (last != i) {
                    max = Math.max(i - last, max);
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestWPI(new int[]{6, 9, 6}));
    }
}
