package com.test.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * TODO 169. 求众数
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class MajorityElement {
    class Solution {
        //自己直接实现
        public int majorityElement(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Integer integer = map.get(nums[i]);
                if (integer == null) {
                    integer = 0;
                }
                map.put(nums[i], ++integer);
                if (integer > nums.length / 2) {
                    return nums[i];
                }
            }
            return 0;
        }
        //直接调用api
        public int majorityElement1(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
        //最优解
        //从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1，减到0就重新换个数开始计数，总能找到最多的那个
        public int majorityElement2(int[] nums) {
            int count = 1;
            int maj = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if(maj == nums[i]){
                    count++;
                }else{
                    count--;
                    if(count == 0){
                        maj = nums[i+1];
                    }
                }
            }
            return maj;
        }

    }
}
