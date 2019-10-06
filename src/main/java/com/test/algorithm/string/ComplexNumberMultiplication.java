package com.test.algorithm.string;
/**
 * TODO 537. 复数乘法
 * 给定两个表示复数的字符串。
 *
 * 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
 *
 * 示例 1:
 *
 * 输入: "1+1i", "1+1i"
 * 输出: "0+2i"
 * 解释: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2:
 *
 * 输入: "1+-1i", "1+-1i"
 * 输出: "0+-2i"
 * 解释: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * 注意:
 *
 * 输入字符串不包含额外的空格。
 * 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/complex-number-multiplication
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class ComplexNumberMultiplication {
    static class Solution {
        //(a+ib)×(x+iy)=ax+i2*by+i*(bx+ay)=ax−by+i*(bx+ay)
        public String complexNumberMultiply(String a, String b) {
            String[] x = a.split("\\+|i");
            String[] y = b.split("\\+|i");
            int aReal = Integer.parseInt(x[0]);
            int aImg = Integer.parseInt(x[1]);
            int bReal = Integer.parseInt(y[0]);
            int bImg = Integer.parseInt(y[1]);
            return (aReal * bReal - aImg * bImg) + "+" + (aReal * bImg + aImg * bReal) + "i";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().complexNumberMultiply("1+1i", "1+1i"));
    }
}
