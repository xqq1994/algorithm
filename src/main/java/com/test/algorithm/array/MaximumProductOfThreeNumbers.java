package com.test.algorithm.array;

import java.util.Arrays;

/**
 * TODO 628. 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class MaximumProductOfThreeNumbers {
    static class Solution {
        //时间复杂度O(nlogn)
        public int maximumProduct(int[] nums) {
            Arrays.sort(nums);
            int a = nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];
            int b = nums[0]*nums[1]*nums[nums.length-1];
            return a>b?a:b;
        }
        //时间复杂度O(n)
        public int maximumProduct1(int[] nums) {
            int max1 = Integer.MIN_VALUE;
            int max2 = Integer.MIN_VALUE;
            int max3 = Integer.MIN_VALUE;
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int num : nums) {
                if(num > max1){
                    max3 = max2;
                    max2 = max1;
                    max1 = num;
                }else if(num > max2){
                    max3 = max2;
                    max2 = num;
                }else if(num > max3){
                    max3 = num;
                }
                if(num < min1){
                   min2 = min1;
                   min1 = num;
                }else if(num < min2){
                    min2 = num;
                }
            }
            return Math.max(max1 * max2 * max3, max1 * min1 * min2);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.maximumProduct1(new int[]{-4,-3,-2,-1,60});
        System.out.println(i);
    }
}

