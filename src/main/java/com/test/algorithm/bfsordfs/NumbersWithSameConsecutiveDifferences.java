package com.test.algorithm.bfsordfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 967. 连续差相同的数字
 * 返回所有长度为 N 且满足其每两个连续位上的数字之间的差的绝对值为 K 的非负整数。
 * <p>
 * 请注意，除了数字 0 本身之外，答案中的每个数字都不能有前导零。例如，01 因为有一个前导零，所以是无效的；但 0 是有效的。
 * <p>
 * 你可以按任何顺序返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：N = 3, K = 7
 * 输出：[181,292,707,818,929]
 * 解释：注意，070 不是一个有效的数字，因为它有前导零。
 * 示例 2：
 * <p>
 * 输入：N = 2, K = 1
 * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 9
 * 0 <= K <= 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/10/10
 */
public class NumbersWithSameConsecutiveDifferences {
    static class Solution {
        public int[] numsSameConsecDiff(int N, int K) {
            if (N == 1) {
                return new int[]{0,1, 2, 3, 4, 5, 6, 7, 8, 9};
            }
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                helper(list,i,0,N,K,0);
            }
            int[] ints = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ints[i] = list.get(i);
            }
            return ints;
        }

        public void helper(List<Integer> list, int temp, int count, int N, int K, int ans) {
            ans += temp;
            count++;
            if(count == N){
                list.add(ans);
                return;
            }
            if (temp - K >= 0) {
                helper(list, temp - K, count, N, K, ans * 10);
            }
            if (temp + K < 10 && K != 0) {
                helper(list, temp + K, count, N, K, ans * 10);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().numsSameConsecDiff(3, 7)));
    }
}
