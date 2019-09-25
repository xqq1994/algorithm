package com.test.algorithm.other;

/**
 * @Description: 405. 数字转换为十六进制数
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 * <p>
 * 注意:
 * <p>
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * 示例 1：
 * <p>
 * 输入:
 * 26
 * <p>
 * 输出:
 * "1a"
 * 示例 2：
 * <p>
 * 输入:
 * -1
 * <p>
 * 输出:
 * "ffffffff"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/25
 */
public class ConvertANumberToHexadecimal {
    static class Solution {
        //使用位运算，每4位，对应1位16进制数字。 将输入右移4位，再做一次，直到输入变为0 这里两个概念了解下：
        //位与(&)：第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0。 无符号右移：位运算符（>>>），
        // 它使用了“零扩展”：无论正负，都在高位插入0。
        public String toHex(int num) {
            if (num == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            String[] res = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
            while (num != 0) {
                sb.append(res[num & 15]);
                num >>>= 4;
            }
            return sb.reverse().toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1 & 15 ));
        System.out.println(new Solution().toHex(-2));
    }
}
