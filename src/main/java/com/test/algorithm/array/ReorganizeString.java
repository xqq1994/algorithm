package com.test.algorithm.array;

/**
 * @Description: 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * <p>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * <p>
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/9
 */
public class ReorganizeString {
    static class Solution {
        public String reorganizeString(String S) {
            String res = "";
            int[] ans = new int[26];
            int max = 0;
            for (int i = 0; i < S.length(); i++) {
                ans[S.charAt(i) - 'a']++;
                max = Math.max(ans[S.charAt(i) - 'a'], max);
            }
            if (max > (S.length() + 1) / 2) {
                return "";
            }
            char last = '*';
            char cur = 0;
            for (int i = 0; i < S.length(); i++) {
                int tmpMax = 0;
                for (char j = 'a'; j <= 'z'; j++) {
                    if (ans[j - 'a'] > 0 && ans[j - 'a'] > tmpMax && last != j) {
                        cur = j;
                        tmpMax = ans[j - 'a'];
                    }
                }
                last = cur;
                ans[cur - 'a']--;
                res += cur;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reorganizeString("aaab"));
    }
}
