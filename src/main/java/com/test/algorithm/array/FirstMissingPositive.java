package com.test.algorithm.array;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 41. 缺失的第一个正数
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 9:49 2019/8/19
 * @Param
 * @return
 */
public class FirstMissingPositive {
    static class Solution {
        /**
         * 根据抽屉原理：数组的长度n，则答案最大只能是n+1；
         * 可以使用hash函数将答案空间映射到长度为n+1的数组上，再遍历数组找到最小的没出现的正整数。
         * 为实现常数空间复杂度，可以使用原数组的空间：
         * 如果 0<nums[i]<=nums.length;
         * 则将nums[i] 跟 nums[nums[i]-1]交换;
         * 最后再遍历数组，如果num[i] != i+1,则i+1就是缺失的最小正整数。
         *
         * **/
        public int firstMissingPositive(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                while (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                    int temp = nums[i];
                    nums[i] = nums[temp - 1];
                    nums[temp - 1] = temp;
                }
            }
            //遍历一次即可找出
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return nums.length + 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[]{3, 4, -1, 1}));
    }
}
