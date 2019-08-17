package com.test.algorithm.greedy;

import java.util.Arrays;

/**
 * TODO 473. 火柴拼正方形
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * <p>
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,2,2,2]
 * 输出: true
 * <p>
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * <p>
 * 输入: [3,3,3,3,4]
 * 输出: false
 * <p>
 * 解释: 不能用所有火柴拼成一个正方形。
 * 注意:
 * <p>
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matchsticks-to-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MatchsticksToSquare {
    static class Solution {
        public boolean makesquare(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            //是否是4的倍数
            if (sum == 0 || sum % 4 != 0) {
                return false;
            }
            int target = sum / 4;
            //有比target大的数直接false
            for (int num : nums) {
                if (num > target) {
                    return false;
                }
            }
            Arrays.sort(nums);
            return dfs(nums, nums.length - 1, new int[4], target);
        }

        public boolean dfs(int[] nums, int pos, int[] sums, int avg) {
            //终止条件
            if (pos == -1) {
                return sums[0] == avg && sums[1] == avg && sums[2] == avg;
            }
            for (int i = 0; i < 4; ++i) {
                //大于所以不满足条件则跳过
                if (sums[i] + nums[pos] > avg) {
                    continue;
                }
                sums[i] += nums[pos];
                //继续遍历剩下的火柴
                if (dfs(nums, pos - 1, sums, avg)) {
                    return true;
                }
                //回溯减去
                sums[i] -= nums[pos];
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().makesquare(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3}));
    }
}
