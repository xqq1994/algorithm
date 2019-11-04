package com.test.algorithm.array;
/**
 *@Description: 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 示例 1：
 *
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 *  
 *
 * 注意:
 *
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/11/4
 */
public class PartitionToKEqualSumSubsets {
    static class Solution {
        //分析：先取平均值avg，也就是判断数组总和/k是否等于整数，这个平均值就是子集的和，小数直接返回false，然后创建一个bool数组，来记录数组是否被用过的状态，
        // 然后初始化一个temp为avg，来记录当前子集的数字总和，当temp==0是说明，当前子集已经产生，index是记录当前遍历数组从哪个位置开始遍历，防止重复计算。
        //当k个子集全部求解完成，返回true，反之为false，当temp==0时，说明新的一个子集确认完成，继而进行下一个子集k-1,并重置temp为avg;
        // 当temp！=0时，说明当前子集为求解完，则继续从数组中取数字，递归求解。
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if(sum % k != 0) return false;
            int avg = sum / k;
            boolean[] flag = new boolean[nums.length];
            return dps(nums,flag,k,avg,avg,0);
        }
        public boolean dps(int[] nums,boolean[] flag,int k,int avg,int temp,int index){
            if(k == 0) return true;
            if(temp == 0) {
                return dps(nums, flag, k - 1, avg, avg, 0);
            }
            for (int i = index; i < nums.length; i++) {
                if(!flag[i] && temp >= nums[i]) {
                    flag[i] = true;
                    if (dps(nums, flag, k, avg, temp - nums[i], index + 1)) {
                        return true;
                    }
                    flag[i] = false;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    }
}
