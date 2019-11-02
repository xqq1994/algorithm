package com.test.algorithm.array;

import java.util.HashMap;

/**
 * TODO 930. 和相同的二元子数组
 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：A = [1,0,1,0,1], S = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 *  
 * <p>
 * 提示：
 * <p>
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] 为 0 或 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class BinarySubarraysWithSum {
    static class Solution {
        public int numSubarraysWithSum(int[] A, int S) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            int res = 0;
            int sum = 0;
            for (int x : A) {
                sum += x;
                res += map.getOrDefault(sum - S, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return res;
        }
        public int numSubarraysWithSum1(int[] A, int S) {
            int psum = 0;
            int res = 0;
            int[] count = new int[A.length + 1];
            count[0] = 1;
            for (int i : A) {
                psum += i;
                if (psum >= S){
                    res += count[psum - S];
                }
                count[psum]++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numSubarraysWithSum1(new int[]{1, 0, 1}, 2));
    }
}
