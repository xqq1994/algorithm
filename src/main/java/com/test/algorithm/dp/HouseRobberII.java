package com.test.algorithm.dp;

import java.util.Arrays;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * @Date 15:05 2019/8/2
 * @Param
 * @return
 */
public class HouseRobberII {
    static class Solution {
        //此算法效率低击败百分之5，嘿嘿
        public int rob(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            } else if (nums.length <= 2) {
                return Arrays.stream(nums).max().getAsInt();
            }
            int[] ints = Arrays.copyOf(nums, nums.length - 1);
            int[] ints2 = Arrays.copyOfRange(nums, 1, nums.length);
            return Math.max(rob1(ints), rob1(ints2));
        }

        public int rob1(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            int[] dp = new int[len + 1];
            dp[0] = 0;
            dp[1] = nums[0];
            for (int i = 2; i <= len; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
            }
            return dp[len];
        }
    }

    public static void main(String[] args) {
        int[] ints = {1};
        System.out.println(new Solution().rob(ints));
    }
}
