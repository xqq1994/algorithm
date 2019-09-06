package com.test.algorithm.array;
/**
 *@Description: 453. 最小移动次数使数组元素相等
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 *
 * 示例:
 *
 * 输入:
 * [1,2,3]
 *
 * 输出:
 * 3
 *
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 *
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/9/6
 */
public class MinimumMovesToEqualArrayElements {
    static class Solution {
        //把题目转化为每个数减一
        public int minMoves(int[] nums) {
            if(nums.length == 0){
                return 0;
            }
            //找到最小值
            int min = nums[0];
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] < min){
                    min = nums[i];
                }
                sum += nums[i];
            }
            //公式answer = SUM(nums) - min * N;
            return sum - nums.length * min;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minMoves(new int[]{1, 1, 1}));
    }
}
