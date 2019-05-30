package com.test.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 1 两数之和
 * @Date 9:03 2019/5/30
 * @Param
 * @return
 */
public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] { map.get(complement), i };
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No two sum solution");
        }
    }
}
