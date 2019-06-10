package com.test.algorithm.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * TODO 20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ValidParentheses {
    static String a = "()";

    public static void main(String[] args) {
        boolean valid = isValid1(a);
        System.out.println(valid);
    }

    //自己的方法实现
    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (stack.size() == 0) {
                if ('}' == c || ')' == c || ']' == c) {
                    return false;
                } else {
                    stack.push(c);
                }
                continue;
            }
            if ('{' == c || '(' == c || '[' == c) {
                stack.push(c);
            } else {
                Character pop = stack.pop();
                if ('}' == c) {
                    if (!(pop == '{')) {
                        if ('}' == c || ')' == c || ']' == c) {
                            return false;
                        }
                        stack.push(pop);
                    }
                }
                if (')' == c) {
                    if (!(pop == '(')) {
                        if ('}' == c || ')' == c || ']' == c) {
                            return false;
                        }
                        stack.push(pop);
                    }
                }
                if (']' == c) {
                    if (!(pop == '[')) {
                        if ('}' == c || ')' == c || ']' == c) {
                            return false;
                        }
                        stack.push(pop);
                    }
                }
            }
        }
        if (stack.size() != 0) {
            return false;
        } else {
            return true;
        }
    }

    //不建议使用
    public static boolean isValid1(String s) {
        int length;
        do {
            length = s.length();
            s = s.replaceAll("\\(\\)", "").replaceAll("\\[\\]", "").replaceAll("\\{\\}", "");
        } while (length != s.length());
        if (s.length() == 0) {
            return true;
        }
        return false;
    }

    //推荐使用
    public static boolean isValid2(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map= new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        for (char c : chars) {
            if(!map.containsKey(c)){
                stack.push(c);
            } else if (stack.isEmpty() || map.get(c) != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
