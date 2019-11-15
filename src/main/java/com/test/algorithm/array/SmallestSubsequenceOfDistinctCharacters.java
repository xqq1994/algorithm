package com.test.algorithm.array;

import java.util.Stack;

/**
 * @Description: 1081. 不同字符的最小子序列
 * 返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："cdadabcc"
 * 输出："adbc"
 * 示例 2：
 * <p>
 * 输入："abcd"
 * 输出："abcd"
 * 示例 3：
 * <p>
 * 输入："ecbacba"
 * 输出："eacb"
 * 示例 4：
 * <p>
 * 输入："leetcode"
 * 输出："letcod"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= text.length <= 1000
 * text 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/15
 */
public class SmallestSubsequenceOfDistinctCharacters {
    static class Solution {
        public String smallestSubsequence(String text) {
            StringBuilder sb = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (stack.contains(c)) {
                    continue;
                }
                while (!stack.isEmpty() && c < stack.peek() && text.indexOf(stack.peek(), i) != -1) {
                    stack.pop();
                }
                stack.push(c);
            }
            for (Character c : stack) {
                sb.append(c);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().smallestSubsequence("leetcode"));
    }
}
