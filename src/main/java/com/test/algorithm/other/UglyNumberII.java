package com.test.algorithm.other;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 264. 丑数 II
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 12:08 2019/7/17
 * @Param
 * @return
 */
public class UglyNumberII {
    static class Solution {
        public int nthUglyNumber(int n) {
            int[] ele = {2, 3, 5};
            PriorityQueue<Long> queue = new PriorityQueue<>();
            long[] res = new long[n];
            res[0] = 1;
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < ele.length; j++) {
                    if (!queue.contains(res[i] * ele[j])) {
                        queue.add(res[i] * ele[j]);
                    }
                }
                if (i + 1 < res.length) {
                    res[i + 1] = queue.poll();
                }
            }
            return (int) res[n - 1];
        }

        public int nthUglyNumber1(int n) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            int t2 = 0, t3 = 0, t5 = 0;
            int curNum = 1;
            for (int i = 1; i < n; i++) {
                curNum = min(list.get(t2) * 2, list.get(t3) * 3, list.get(t5) * 5);
                list.add(curNum);
                if (list.get(t2) * 2 == list.get(i)) t2++;
                if (list.get(t3) * 3 == list.get(i)) t3++;
                if (list.get(t5) * 5 == list.get(i)) t5++;
            }
            if (n == 0)
                return 0;
            else return list.get(n - 1);
        }

        public int min(int a, int b, int c) {
            return Math.min(Math.min(a, b), c);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber1(10));
    }

}
