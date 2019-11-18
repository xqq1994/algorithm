package com.test.algorithm.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 1238. 循环码排列
 * 给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
 * <p>
 * p[0] = start
 * p[i] 和 p[i+1] 的二进制表示形式只有一位不同
 * p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2, start = 3
 * 输出：[3,2,0,1]
 * 解释：这个排列的二进制表示是 (11,10,00,01)
 * 所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
 * 示例 2：
 * <p>
 * 输出：n = 3, start = 2
 * 输出：[2,6,7,5,4,0,1,3]
 * 解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 16
 * 0 <= start < 2^n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/circular-permutation-in-binary-representation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/18
 */
public class CircularPermutationInBinaryRepresentation {
    static class Solution {
        public List<Integer> circularPermutation(int n, int start) {
            //生成格雷码
            //https://blog.csdn.net/dongyanwen6036/article/details/86557671
            List<Integer> result = new ArrayList<>();
            result.add(start);
            for (int i = 1; i < (1 << n); i++) {
                //将start转为二进制
                //加一取余操作后转为格雷码
                result.add(getGrayCode((getBinaryCode(start) + i) % (1 << n)));
            }
            return result;
        }

        public int getGrayCode(int x) {
            //G：格雷码 B：二进制码 n：正在计算的位
            //根据格雷码的定义可得：
            //G(n) = B(n+1) XOR B(n)
            return x ^ (x >> 1);
        }

        public int getBinaryCode(int x) {
            int y = x;
            while ((x >>= 1) >= 1) {
                y ^= x;
            }
            return y;
        }
    }
    public static void main(String[] args) {
        System.out.println(new Solution().circularPermutation(2, 3));
    }

}
