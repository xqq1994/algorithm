package com.test.algorithm.dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 *@Description: 862. 和至少为 K 的最短子数组
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 *
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1], K = 1
 * 输出：1
 * 示例 2：
 *
 * 输入：A = [1,2], K = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/11/12
 */
public class ShortestSubarrayWithSumAtLeastL {
    static class Solution {
        //暴力
        public int shortestSubarray(int[] A, int K) {
            int minLen = A.length + 1;
            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A.length; j++) {
                    int sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum += A[k];
                    }
                    if(sum >= K && (j - i + 1) < minLen){
                        minLen = j - i + 1;
                    }
                }
            }
            return minLen == A.length + 1 ? -1 : minLen;
        }
        //preSum[i] = A[0] + A[1] + ... + A[i-1]
        public int shortestSubarray1(int[] A, int K) {
            int minLen = A.length + 1;
            int[] preSum = new int[A.length + 1];
            preSum[0] = 0;
            for (int i = 0; i < A.length; i++) {
                preSum[i + 1] = preSum[i] + A[i];
            }
            for (int i = 0; i < A.length; i++) {
                for (int j = i + 1; j < A.length + 1; j++) {
                    if(preSum[j] - preSum[i] >= K){
                        if((j - i) < minLen){
                            minLen = j - i;
                        }
                    }
                }
            }
            return minLen == A.length + 1 ? -1 : minLen;
        }
        public int shortestSubarray2(int[] A, int K) {
            int len = A.length;
            int minLen = len + 1;
            int[] preSum = new int[len + 1];
            for (int i = 0; i < len; i++) {
                preSum[i + 1] = preSum[i] + A[i];
            }
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < A.length + 1; i++) {
                while (!deque.isEmpty() && preSum[i] <= preSum[deque.getLast()]){
                    //比如当preSum[x2] <= preSum[x1]（其中x1 < x2）时，表明x1到x2之间的元素的和是负数或0，那么就是当preSum[xn] - preSum[x1] >= K则必然有preSum[xn] - preSum[x2] >= K，
                    // 那么这个时候我们只计算xn - x2即可（x1到x2之间的元素可以全部跳过了，耶！），就不需要计算xn - x1了，因为后者一定是更大的，不满足我们要选最小的条件。
                    deque.pollLast();
                }
                while (!deque.isEmpty() && preSum[i] - preSum[deque.getFirst()] >= K){
                    //另一个角度，当preSum[x2] - preSum[x1] >= K时，x1就可以跳过了，为什么呢？因为x1到x2已经满足了大于K，再继续从x1开始向后再早，也不会再有比x2距离x1更近的了，
                    //毕竟我们要求的是最小的x2 - x1。
                    minLen = Math.min(minLen, i - deque.pollFirst());
                }
                //以上的两种分析，情况1是把位于末尾没用的x1扔掉，情况2是把指向前面的已经满足条件的x1的指针向后移动1位，这是就需要比较当前最小值与此时刚符合条件的值的大小了。
                deque.add(i);
            }
            return minLen == len + 1 ? -1 : minLen;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestSubarray2(new int[]{2, -1, 2}, 3));
    }
}
