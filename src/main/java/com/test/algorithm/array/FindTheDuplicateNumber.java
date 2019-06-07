package com.test.algorithm.array;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 287 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @Date 13:14 2019/5/30
 * @Param
 * @return
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 *
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 *
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
