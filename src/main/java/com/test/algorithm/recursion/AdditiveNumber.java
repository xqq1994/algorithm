package com.test.algorithm.recursion;

/**
 * @Description: 306. 累加数
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * <p>
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * <p>
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 * <p>
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2:
 * <p>
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/16
 */
public class AdditiveNumber {
    class Solution {
        public boolean isAdditiveNumber(String num) {
            int len = num.length();
            //确定第一个数，最终用num.subStr(0,i)来确定第一个数，所以i可以用来标示第一个数的长度，
            //但是下标i不包含在第一个数中
            for (int i = 1; i <= len / 2; i++) {
                //如果长度大于等于2，则不能以0开头
                if (num.startsWith("0") && i >= 2) {
                    break;
                }
                //确定第二个数，第一个数用num.subStr(i,j),包括i，不包括j，所以长度为j-i,
                //第三个数从下标j开始，长度最长为L-1-j+1，即L-j
                for (int j = i + 1; (len - j) >= i && (len - j) >= j - i; j++) {
                    if (num.charAt(i) == '0' && j - i >= 2) {
                        break;
                    }
                    long num1 = Long.parseLong(num.substring(0, i));
                    long num2 = Long.parseLong(num.substring(i, j));
                    if (isValid(num.substring(j), num1, num2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //判断由num1,num2和后续的字串能否构成加法序列
        public boolean isValid(String remain, long num1, long num2) {
            if (remain.equals("")) {
                return true; //没有剩下的了
            }
            long sum = num1 + num2;
            String sumStr = "" + sum;
            if (!remain.startsWith(sumStr)) {
                return false;
            }
            return isValid(remain.substring(sumStr.length()), num2, sum);
        }
    }
}
