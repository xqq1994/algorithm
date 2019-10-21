package com.test.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 *@Description: 525. 连续数组
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 *
 * 示例 1:
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *
 * 示例 2:
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 注意: 给定的二进制数组的长度不会超过50000。
 *@Author: xiaoqiangqiang
 *@date: 2019/10/21
 */
public class ContiguousArray {
    static class Solution {
        public int findMaxLength(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            int maxLen = 0,count = 0;
            map.put(0,-1);
            for (int i = 0; i < nums.length; i++) {
                count += nums[i] == 1 ? 1 : -1;
                if (map.containsKey(count)) {
                    maxLen = Math.max(maxLen,i - map.get(count));
                }else{
                    map.put(count,i);
                }
            }
            return maxLen;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMaxLength(new int[]{0, 1}));
    }
}
