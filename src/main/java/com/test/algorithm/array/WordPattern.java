package com.test.algorithm.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 * **/
public class WordPattern {
    static class Solution {
        //使用一个set和一个hashmap存值
        public boolean wordPattern(String pattern, String str) {
            char[] chars = pattern.toCharArray();
            String[] s = str.split(" ");
            if(chars.length != s.length) {
                return false;
            }
            //存储pattern->str对应键值对
            HashMap<Character, String> map = new HashMap<>();
            //存储str不重复的value
            Set<String> set = new HashSet<>();
            for (int i = 0; i < chars.length; i++) {
                String s1 = map.get(chars[i]);
                if(s1==null){
                    if(!set.contains(s[i])){
                        set.add(s[i]);
                        map.put(chars[i],s[i]);
                    }else{
                        return false;
                    }
                }else{
                    //如果遇到不相等的值
                    if(!s1.equals(s[i])){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.wordPattern("abba","a b b a");
        System.out.println(b);
    }
}
