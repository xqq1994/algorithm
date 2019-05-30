package com.test.algorithm.array;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 287 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @Date 13:14 2019/5/30
 * @Param
 * @return
 */
public class FindTheDuplicateNumber {
    /**
     * @return
     * @Author xiaoqiangqiang
     * @Description //TODO hashset判断但是空间复杂度为O(n),舍弃
     * @Date 14:26 2019/5/30
     * @Param
     */
    /*public int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                return nums[i];
            }else{
                set.add(nums[i]);
            }
        }
        return 0;
    }*/
    public static void main(String[] args) {
        int[] a = new int[]{1,3,4,2,2};
        int duplicate = findDuplicate(a);
        System.out.println(duplicate);

    }
    /**
     * @Author xiaoqiangqiang
     * @Description //TODO 快慢指针写法
     * @Date 16:12 2019/5/30
     * @Param 
     * @return 
     */
    public static int findDuplicate(int[] nums) {
        int slow = 0,fast = 0;
        while (true){
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow){
                fast = 0;
                while (nums[slow] != nums[fast]){
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }
}
