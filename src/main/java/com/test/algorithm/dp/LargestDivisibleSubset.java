package com.test.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Description: 368. 最大整除子集
 * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
 *
 * 如果有多个目标子集，返回其中任何一个均可。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2] (当然, [1,3] 也正确)
 * 示例 2:
 *
 * 输入: [1,2,4,8]
 * 输出: [1,2,4,8]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/10/31
 */
public class LargestDivisibleSubset {
    static class Solution {
        public List<Integer> largestDivisibleSubset(int[] nums) {
            int n = nums.length;
            List<Integer> res = new ArrayList();
            if (n == 0) return res;
            Arrays.sort(nums);
            int[] count = new int[n];
            int[] pre = new int[n]; // 记录前面索引
            Arrays.fill(count, 1);
            int max = 1, index = 0; // 最大子序列长度
            for (int i = 0; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] % nums[j] == 0 && count[i] < count[j] + 1) {
                        count[i] = count[j] + 1;
                        pre[i] = j;
                        if (count[i] > max) {
                            max = count[i]; // maxCount
                            index = i; // index
                        }
                    }
                }
            }
            while (max > 0) {
                res.add(0, nums[index]);
                index = pre[index];
                max--;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().largestDivisibleSubset(new int[]{1,2,4,8}));
    }
}
