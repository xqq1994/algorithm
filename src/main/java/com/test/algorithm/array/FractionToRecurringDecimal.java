package com.test.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 *@Description: 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 *
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * 示例 1:
 *
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 *
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 *
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/8/30
 */
public class FractionToRecurringDecimal {
    static class Solution {
        public String fractionToDecimal(int numerator, int denominator) {
            StringBuilder builder = new StringBuilder();
            if(numerator==0){
                return "0";
            }
            if(denominator == 0){
                return "";
            }
            if(numerator < 0 ^ denominator < 0){
                builder.append("-");
            }
            long d1 = Math.abs((long)numerator);
            long d2 = Math.abs((long)denominator);
            long m = d1 / d2;
            builder.append(m);
            if(d1 % d2 == 0){
                return builder.toString();
            }
            builder.append(".");
            Map<Long, Integer> map = new HashMap<>();
            long k = d1 % d2;
            while (k != 0){
                if(map.get(k) != null){
                    builder.insert(map.get(k),"(");
                    builder.append(")");
                    return builder.toString();
                }
                map.put(k,builder.length());
                k = k * 10;
                builder.append(k / d2);
                k = k % d2;
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().fractionToDecimal(2, 1));
    }
}
