package com.test.algorithm.array;

/**
 * @Description: 713. 乘积小于K的子数组
 * 给定一个正整数数组 nums。
 * <p>
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 说明:
 * <p>
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/24
 */
public class SubarrayProductLessThanK {
    static class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if(k <= 1){
                return 0;
            }
            int count = 0, left = 0, one = 1;
            for (int right = 0; right < nums.length; right++) {
                one *= nums[right];
                while (one >= k){
                    one /= nums[left++];
                }
                count += right - left + 1;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
    }
}
