package com.test.algorithm.dp;

import java.util.Arrays;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * @Date 12:19 2019/8/1
 * @Param
 * @return
 */
public class LongestIncreasingSubsequence {
    static class Solution {
        //【关键】将 dp 数组定义为：以 nums[i] 结尾的最长上升子序列的长度
        // 那么题目要求的，就是这个 dp 数组中的最大者
        // 以数组  [10, 9, 2, 5, 3, 7, 101, 18] 为例：
        // dp 的值： 1  1  1  2  2  3  4    4
        // 注意实现细节。
        public int lengthOfLIS(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            // 状态的定义是：以 i 结尾的最长上升子序列的长度
            // 状态转移方程：之前比最后那个数字小的最长上升子序列的长度 + 1
            int[] dp = new int[len];
            // 如果只有 1 个元素，那么这个元素自己就构成了最长上升子序列，所以设置为 1 是合理的
            Arrays.fill(dp, 1);
            // 从第 2 个元素开始，逐个写出 dp 数组的元素的值
            for (int i = 1; i < len; i++) {
                int curVal = nums[i];
                // 找出比当前元素小的哪些元素的最小值
                for (int j = 0; j < i; j++) {
                    if (curVal > nums[j]) {
                        dp[i] = Integer.max(dp[j] + 1, dp[i]);
                    }
                }
            }
            // 最后要全部走一遍，看最大值
            int res = dp[0];
            for (int i = 0; i < len; i++) {
                res = Integer.max(res, dp[i]);
            }
            return res;
        }
        public int lengthOfLIS1(int[] nums) {
            int len = nums.length;
            // 特判
            if (len <= 1) {
                return len;
            }
            // tail 数组的定义：长度为 i + 1 的上升子序列的末尾最小是几
            int[] tail = new int[len];
            // 遍历第 1 个数，直接放在有序数组 tail 的开头
            tail[0] = nums[0];
            // end 表示有序数组 tail 的最后一个已经赋值元素的索引

            int end = 0;
            for (int i = 1; i < len; i++) {
                // 【逻辑 1】比 tail 数组实际有效的末尾的那个元素还大
                if (nums[i] > tail[end]) {
                    // 直接添加在那个元素的后面，所以 end 先加 1
                    end++;
                    tail[end] = nums[i];
                } else {
                    // 使用二分查找法，在有序数组 tail 中
                    // 找到第 1 个大于等于 nums[i] 的元素，尝试让那个元素更小
                    int left = 0;
                    int right = end;
                    while (left < right) {
                        // 选左中位数不是偶然，而是有原因的，原因请见 LeetCode 第 35 题题解
                        // int mid = left + (right - left) / 2;
                        int mid = left + ((right - left) >>> 1);
                        if (tail[mid] < nums[i]) {
                            // 中位数肯定不是要找的数，把它写在分支的前面
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    // 走到这里是因为 【逻辑 1】 的反面，因此一定能找到第 1 个大于等于 nums[i] 的元素
                    // 因此，无需再单独判断
                    tail[left] = nums[i];
                }
                // 调试方法
                 printArray(nums[i], tail);
            }
            // 此时 end 是有序数组 tail 最后一个元素的索引
            // 题目要求返回的是长度，因此 +1 后返回
            end++;
            return end;
        }

        // 调试方法，以观察是否运行正确
        private void printArray(int num, int[] tail) {
            System.out.print("当前数字：" + num);
            System.out.print("\t当前 tail 数组：");
            int len = tail.length;
            for (int i = 0; i < len; i++) {
                if (tail[i] == 0) {
                    break;
                }
                System.out.print(tail[i] + ", ");
            }
            System.out.println();
        }

        public static void main(String[] args) {
            int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
            Solution solution = new Solution();
            int lengthOfLIS = solution.lengthOfLIS1(nums);
            System.out.println(lengthOfLIS);
        }
    }
}
