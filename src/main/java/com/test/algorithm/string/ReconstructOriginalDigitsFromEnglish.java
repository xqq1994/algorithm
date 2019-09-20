package com.test.algorithm.string;
/**
 *@Description: 423. 从英文中重建数字
 * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
 *
 * 注意:
 *
 * 输入只包含小写英文字母。
 * 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
 * 输入字符串的长度小于 50,000。
 * 示例 1:
 *
 * 输入: "owoztneoer"
 *
 * 输出: "012" (zeroonetwo)
 * 示例 2:
 *
 * 输入: "fviefuro"
 *
 * 输出: "45" (fourfive)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/9/20
 */
public class ReconstructOriginalDigitsFromEnglish {
    static class Solution {
        public String originalDigits(String s) {
            char[] count = new char[26 + 'a'];
            for (char c : s.toCharArray()) {
                count[c]++;
            }
            int[] res = new int[10];
            res[0] = count['z'];
            res[2] = count['w'];
            res[4] = count['u'];
            res[6] = count['x'];
            res[8] = count['g'];
            res[3] = count['h'] - res[8];
            res[5] = count['f'] - res[4];
            res[7] = count['s'] - res[6];
            res[9] = count['i'] - res[6] - res[8] -res[5];
            res[1] = count['n'] - res[7] - 2 * res[9];
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < res.length; i++) {
                if(res[i] != 0){
                    for (int j = 1; j <= res[i]; j++) {
                        builder.append(i);
                    }
                }
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().originalDigits("owoztneoertwo"));
    }
}
