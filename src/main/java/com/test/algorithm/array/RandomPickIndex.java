package com.test.algorithm.array;

import java.util.*;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 398. 随机数索引
 * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * <p>
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 * <p>
 * 示例:
 * <p>
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
 * solution.pick(3);
 * <p>
 * // pick(1) 应该返回 0。因为只有nums[0]等于1。
 * solution.pick(1);
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 9:21 2019/8/20
 * @Param
 * @return
 */
public class RandomPickIndex {
    class Solution {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Random rmd = new Random();

        public Solution(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i])) {
                    List<Integer> integer = map.get(nums[i]);
                    integer.add(i);
                    map.put(nums[i], integer);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(nums[i], list);
                }

            }
        }

        public int pick(int target) {
            List<Integer> integers = map.get(target);
            int a = rmd.nextInt(integers.size());
            return integers.get(a);
        }

    }

    class Solution1 {
        int[] nums;
        Random random;

        public Solution1(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }

        public int pick(int target) {
            int k = 0;
            int res = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    int index = random.nextInt(k + 1);
                    if (index == k) {
                        res = i;
                    }
                    k++;
                }
            }
            return res;
        }
    }
}
