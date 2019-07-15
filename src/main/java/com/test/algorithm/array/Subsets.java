package com.test.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class Subsets {
    static class Solution {
        //回溯算法
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            getAns(nums, 0, new ArrayList<>(), ans);
            return ans;
        }

        private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
            ans.add(new ArrayList<>(temp));
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                getAns(nums, i + 1, temp, ans);
                temp.remove(temp.size() - 1);
            }
        }
        //位运算方法很巧妙
        public List<List<Integer>> subsets3(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            for (int i = 0; i < (1 << nums.length); i++) {
                List<Integer> sub = new ArrayList<>();
                for (int j = 0; j < nums.length; j++)
                    if (((i >> j) & 1) == 1) sub.add(nums[j]);
                res.add(sub);
            }
            return res;
        }
        //逐个枚举，空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。
        public List<List<Integer>> subsets1(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<Integer>());
            for (Integer n : nums) {
                int size = res.size();
                for (int i = 0; i < size; i++) {
                    List<Integer> newSub = new ArrayList<>(res.get(i));
                    newSub.add(n);
                    res.add(newSub);
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{1,2,3};
        List<List<Integer>> subsets = solution.subsets(a);
        System.out.println(subsets);
    }
}
