package com.test.algorithm.array;

import java.util.Random;

/**
 * TODO 384. 打乱数组
 * 打乱一个没有重复元素的数组。
 * <p>
 * 示例:
 * <p>
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * <p>
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * <p>
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
class ShuffleAnArray {
    private int[] array;
    private int[] original;
    private Random random = new Random();
    public ShuffleAnArray(int[] nums) {
        original = nums;
        array = original.clone();
    }


    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return original.clone();
    }
    public int rangRange(int min,int max){
        return random.nextInt(max - min) + min;
    }
    public void swap(int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int j = 0; j < array.length; j++) {
            swap(j,rangRange(j,array.length));
        }
        return array;
    }
}
