package com.test.algorithm.queue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * TODO 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 注意：
 * <p>
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SlidingWindowMaximum {
    static class Solution {
        /**双向链表法**/
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            Deque<Integer> deque = new LinkedList<>();
            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                    deque.pollLast();
                }
                deque.addLast(i);
                if (deque.peek() <= i - k) {
                    deque.poll();
                }
                if (i - k + 1 >= 0) {
                    result[i - k + 1] = nums[deque.peek()];
                }
            }
            return result;
        }
        /**直接优先级队列法**/
        public int[] maxSlidingWindow1(int[] nums, int k) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
            int[] result = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                if(i >= k){
                    queue.remove(nums[i-k]);
                }
                queue.offer(nums[i]);
                if(i - k + 1>= 0){
                    result[i - k +1] = queue.peek();
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] result = new int[]{3, 2, 1, 4};
        int[] ints = solution.maxSlidingWindow(result, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
