package com.test.algorithm.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 *@Description: 354. 俄罗斯套娃信封问题
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/8/29
 */
public class RussianDollEnvelopes {
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            int length = envelopes.length;
            Arrays.sort(envelopes, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
                }
            });
            int[] height = new int[length];
            for (int i = 0; i < length; i++) {
                height[i] = envelopes[i][1];
            }
            return lengthOfLIS(height);
        }
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
    }
}
