package com.test.algorithm.bitoperation;
/**
 *@Description: 461. 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/9/5
 */
public class HammingDistance {
    static class Solution {
        //自己转二进制之后判断不同
        public int hammingDistance(int x, int y) {
            String xx = toBin(x);
            String yy = toBin(y);
            int a = 0;
            for (int i = 0; i < xx.length(); i++) {
                if(xx.charAt(i) != yy.charAt(i)){
                    a++;
                }
            }
            return a;
        }
        public String toBin(int val){
            char[] chars = new char[Integer.SIZE];
            for (int i = 0; i < chars.length; i++) {
                chars[Integer.SIZE - 1 - i] = (char)((val >> i & 1) + '0');
            }
            return new String(chars);
        }
        //一行代码实现
        public int hammingDistance1(int x, int y) {
            return Integer.bitCount(x^y);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hammingDistance(1, 4));
    }
}
