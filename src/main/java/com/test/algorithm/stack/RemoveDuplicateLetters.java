package com.test.algorithm.stack;

import java.util.Stack;

/**
 *@Description: //TODO 316. 去除重复字母
 * 给定一个仅包含小写字母的字符串，去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1:
 *
 * 输入: "bcabc"
 * 输出: "abc"
 * 示例 2:
 *
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/8/27
 */
public class RemoveDuplicateLetters {
    static class Solution {
        public String removeDuplicateLetters(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                if(stack.contains(c)){
                    continue;
                }else{
                    //遇到一个新字符 如果比栈顶小 并且在新字符后面还有和栈顶一样的 就把栈顶的字符抛弃了
                    while (!stack.isEmpty()
                            && stack.peek() > c
                            && s.indexOf(stack.peek(),i) != -1){
                        stack.pop();
                    }
                    stack.push(c);
                }
            }
            char[] chars = new char[stack.size()];
            for (int i = 0; i < stack.size(); i++) {
                chars[i] = stack.get(i);
            }
            return String.valueOf(chars);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeDuplicateLetters("bcabc"));
    }
}
