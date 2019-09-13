package com.test.algorithm.array;

import java.util.Stack;

/**
 * TODO 456. 132模式
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * <p>
 * 注意：n 的值小于15000。
 * <p>
 * 示例1:
 * <p>
 * 输入: [1, 2, 3, 4]
 * <p>
 * 输出: False
 * <p>
 * 解释: 序列中不存在132模式的子序列。
 * 示例 2:
 * <p>
 * 输入: [3, 1, 4, 2]
 * <p>
 * 输出: True
 * <p>
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/132-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Pattern132 {
    static class Solution {
        public boolean find132pattern(int[] nums) {
            if (nums.length < 3) {
                return false;
            }
            int second = Integer.MIN_VALUE;
            Stack<Integer> stack = new Stack<>();
            stack.add(nums[nums.length - 1]);
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < second) {
                    return true;
                } else {
                    while (!stack.isEmpty() && stack.peek() < nums[i]) {
                        second = Math.max(stack.pop(), second);
                    }
                    stack.push(nums[i]);
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().find132pattern(new int[]{3, 1, 4, 2}));
    }
}
