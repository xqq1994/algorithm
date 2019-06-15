package com.test.algorithm.map;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class ValidAnagram {
    class Solution {
        //首先想到的方法 hash映射发
        public boolean isAnagram(String s, String t) {
            if(s.length() != t.length()) {
                return false;
            }
            char[] chars = s.toCharArray();
            char[] chars1 = t.toCharArray();
            Map<Character, Integer> map = new HashMap<>();
            Map<Character, Integer> map1 = new HashMap<>();
            for (char aChar : chars) {
                Integer integer = map.get(aChar);
                if(integer == null){
                    integer = 1;
                }else{
                    integer++;
                }
                map.put(aChar,integer);
            }
            for (char aChar : chars1) {
                Integer integer = map1.get(aChar);
                if(integer == null){
                    integer = 1;
                }else{
                    integer++;
                }
                map1.put(aChar,integer);
            }
            if(map.equals(map1)){
                return true;
            }
            return false;
        }
        //推荐写法
        public boolean isAnagram1(String s, String t) {
            int[] arr = new int[26];
            for(char c : s.toCharArray()) arr[c-'a']++;
            for(char c : t.toCharArray()) arr[c-'a']--;
            for(int x : arr) if(x != 0) return false;
            return true;
        }
    }
}
