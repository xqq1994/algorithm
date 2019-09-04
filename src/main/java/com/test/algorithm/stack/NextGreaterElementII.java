package com.test.algorithm.stack;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.Stack;

/**
 *@Description: 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/9/4
 */
public class NextGreaterElementII {
    static class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] ans = new int[n];
            Arrays.fill(ans,-1);
            Stack<Integer> stack = new Stack<>();
            //遍历两边相当于循环
            for (int i = 0; i < n * 2; i++) {
                //循环主要方法
                int num = nums[i % n];
                //判断栈顶元素和当前元素的大小
                //若栈顶元素 < 当前元素：弹出栈顶元素，并记录栈顶元素的下一个更大元素为当前元素
                while (!stack.isEmpty() && num > nums[stack.peek()]){
                    ans[stack.pop()] = num;
                }
                //若栈顶元素 > 当前元素：当前元素入栈
                if(i < n) {
                    stack.push(i);
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new Solution().nextGreaterElements(new int[]{2, 1, 2, 4, 3})));

    }
}
