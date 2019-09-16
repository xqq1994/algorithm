package com.test.algorithm.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: 448. 找到所有数组中消失的数字
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/16
 */
public class FindAllNumbersDisappearedInAnArray {
    static class Solution {
        //又长又丑的代码

        /**
         * 执行用时 :80 ms, 在所有 Java 提交中击败了5.33%的用户
         * 内存消耗 :61.1 MB, 在所有 Java 提交中击败了5.15%的用户
         **/
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> list = new ArrayList<>();
            HashSet<Integer> set = new HashSet<>();
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                }
                if (nums[i] < min) {
                    min = nums[i];
                }
                set.add(nums[i]);
            }
            if (max < nums.length) {
                max = nums.length;
            }
            if (min > 1) {
                min = 1;
            }
            if (min == max) {
                if (min == nums.length) {
                    list.add(nums[0] - 1);
                } else {
                    list.add(nums[0] + 1);
                }
                return list;
            }
            for (int i = min; i <= max; i++) {
                if (!set.contains(i)) {
                    list.add(i);
                }
            }
            return list;
        }

        /**
         * 找出 1 - n 中没有出现的数字。不能使用额外的空间，两次循环时间复杂度为 2O(n)，即为 O(n)。
         * <p>
         * 解题思路：使用数组的下标来标记数字的出现于否，通过一遍遍历即可标记出全部已经出现的数组
         * <p>
         * [4,3,2,7,8,2,3,1] 初始数据
         * <p>
         * [4,3,2,-7,8,2,3,1] 第一个数据 4 出现，将数组的第四个也就是下标 3 的数据修改为负数。-7 计算时，通过绝对值处理一下即可不影响数据的计算
         * [4,3,-2,-7,8,2,3,1]
         * [4,-3,-2,-7,8,2,3,1]
         * [4,-3,-2,-7,8,2,-3,1]
         * [4,-3,-2,-7,8,2,-3,-1]
         * [4,-3,-2,-7,8,2,-3,-1]
         * [4,-3,-2,-7,8,2,-3,-1]
         * [-4,-3,-2,-7,8,2,-3,-1]
         * <p>
         * 计算结束，数组的第五个，第六个依然为整数，证明 5,6 没有出现
         *
         * @param nums
         * @return
         */
        public List<Integer> findDisappearedNumbers1(int[] nums) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int temp = -Math.abs(nums[Math.abs(nums[i]) - 1]);
                nums[Math.abs(nums[i]) - 1] = temp;
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    list.add(i + 1);
                }
            }
            return list;
        }

        //最优解
        public List<Integer> findDisappearedNumbers2(int[] nums) {
            List<Integer> ret = new ArrayList<>();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                //取模
                int index = (nums[i] - 1) % n;
                nums[index] += n;
            }
            for (int i = 0; i < n; i++) {
                if (nums[i] <= n) {
                    ret.add(i + 1);
                }
            }
            return ret;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDisappearedNumbers2(new int[]{5, 4, 6, 7, 9, 3, 10, 9, 5, 6}));
    }
}
