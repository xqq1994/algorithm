package com.test.algorithm.string;

/**
 * TODO 848. 字母移位
 * 有一个由小写字母组成的字符串 S，和一个整数数组 shifts。
 * <p>
 * 我们将字母表中的下一个字母称为原字母的 移位（由于字母表是环绕的， 'z' 将会变成 'a'）。
 * <p>
 * 例如·，shift('a') = 'b'， shift('t') = 'u',， 以及 shift('z') = 'a'。
 * <p>
 * 对于每个 shifts[i] = x ， 我们会将 S 中的前 i+1 个字母移位 x 次。
 * <p>
 * 返回将所有这些移位都应用到 S 后最终得到的字符串。
 * <p>
 * 示例：
 * <p>
 * 输入：S = 'abc', shifts = [3,5,9]
 * 输出：'rpl'
 * 解释：
 * 我们以 'abc' 开始。
 * 将 S 中的第 1 个字母移位 3 次后，我们得到 'dbc'。
 * 再将 S 中的前 2 个字母移位 5 次后，我们得到 'igc'。
 * 最后将 S 中的这 3 个字母移位 9 次后，我们得到答案 'rpl'。
 * 提示：
 * <p>
 * 1 <= S.length = shifts.length <= 20000
 * 0 <= shifts[i] <= 10 ^ 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shifting-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ShiftingLetters {
    static class Solution {
        public String shiftingLetters(String S, int[] shifts) {
            char[] chars = S.toCharArray();
            char[] aa = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            for (int j = 0; j < shifts.length; j++) {
                for (int i = S.length() - 1 - j; i >= 0; i--) {
                    int i1 = shifts[S.length() - 1 - j] % 26;
                    int i2 = chars[i] - 'a';
                    chars[i] = aa[(i2 + i1) % 26];
                }
            }
            return new String(chars);
        }

        public String shiftingLetters1(String S, int[] shifts) {
            int acc = 0;
            for (int i = shifts.length - 1; i >= 0; i--) {
                shifts[i] = shifts[i] % 26;
                shifts[i] += acc;
                acc = shifts[i];
            }
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < S.length(); i++) {
                buffer.append((char) ((S.charAt(i) - 'a' + shifts[i]) % 26 + 'a'));
            }
            return buffer.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shiftingLetters1("bad", new int[]{10, 20, 30}));
    }
}
