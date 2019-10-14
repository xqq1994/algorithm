package com.test.algorithm.greedy;

import java.util.*;

/**
 * @Description: 870. 优势洗牌
 * 给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
 * <p>
 * 返回 A 的任意排列，使其相对于 B 的优势最大化。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [2,7,11,15], B = [1,10,4,11]
 * 输出：[2,11,7,15]
 * 示例 2：
 * <p>
 * 输入：A = [12,24,8,32], B = [13,25,32,11]
 * 输出：[24,32,8,12]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/advantage-shuffle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/10/14
 */
public class AdvantageShuffle {
    static class Solution {
        public int[] advantageCount(int[] A, int[] B) {
            int[] cloneA = A.clone();
            Arrays.sort(cloneA);
            int[] cloneB = B.clone();
            Arrays.sort(cloneB);
            Map<Integer, Deque<Integer>> assigned = new HashMap<>();
            for (int i : cloneB) {
                assigned.put(i,new LinkedList<>());
            }
            Deque<Integer> remaining = new LinkedList<>();
            int k = 0;
            for (int i : cloneA) {
                if (i > cloneB[k]) {
                    assigned.get(cloneB[k++]).add(i);
                } else {
                    remaining.add(i);
                }
            }
            int[] newr = new int[cloneB.length];
            for (int i = 0; i < B.length; i++) {
                if(assigned.get(B[i]).size() > 0){
                     newr[i]= assigned.get(B[i]).pop();
                }else{
                    newr[i] = remaining.pop();
                }
            }
            return newr;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11})));
    }
}
