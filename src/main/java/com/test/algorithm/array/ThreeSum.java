package com.test.algorithm.array;

import java.util.*;

/**
 * TODO 15. 三数之和
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ThreeSum {

    class Solution {
        //用map实现
        public List<List<Integer>> threeSum(int[] nums) {
            if (nums == null || nums.length < 3) {
                return new ArrayList();
            }
            List<List<Integer>> resultList = new ArrayList<>();
            //要先对数组进行排序
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                //重复的过滤掉
                if (i > 0 && nums[i] == nums[i - 1])
                    continue;
                Map<Integer, Integer> targetMap = new HashMap<>();
                for (int j = i + 1; j < nums.length; j++) {
                    if (targetMap.containsKey(nums[j])) {
                        if (targetMap.get(nums[j]) == 0) {
                            resultList.add(Arrays.asList(nums[i], nums[j], -nums[i] - nums[j]));
                            targetMap.put(nums[j], 1);
                        } else {
                            targetMap.put(-nums[i] - nums[j], 0);
                        }
                    }

                }
            }
            return resultList;
        }

        //用双指针实现
        public List<List<Integer>> threeSum1(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> ls = new ArrayList<>();
            for (int i = 0; i < nums.length - 2; i++) {
                if (i == 0 || nums[i] != nums[i - 1]) {
                    int l = i + 1, r = nums.length - 1;
                    while (l < r){
                        int sum = nums[i] + nums[l] +nums[r];
                        if(sum > 0){
                            r--;
                        }else if(sum < 0){
                            l++;
                        }else{
                            ArrayList<Integer> integers = new ArrayList<>();
                            integers.add(nums[i]);
                            integers.add(nums[l]);
                            integers.add(nums[r]);
                            ls.add(integers);
                            while (l < r && nums[l] == nums[l + 1]){
                                l++;
                            }
                            while (l < r && nums[r] == nums[r - 1]){
                                r--;
                            }
                            l++;
                            r--;
                        }
                    }
                }
            }
            return ls;
        }
    }

}
