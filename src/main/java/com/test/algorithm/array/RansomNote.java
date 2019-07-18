package com.test.algorithm.array;

import java.util.HashMap;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 383. 赎金信
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
 *
 * 注意：
 *
 * 你可以假设两个字符串均只含有小写字母。
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ransom-note
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 12:23 2019/7/18
 * @Param
 * @return
 */
public class RansomNote {
    static class Solution {
        //自己用hashmap实现
        public boolean canConstruct(String ransomNote, String magazine) {
            HashMap<Integer,Integer> set = new HashMap<>();
            char[] chars = ransomNote.toCharArray();
            char[] chars1 = magazine.toCharArray();
            for (char c : chars) {
                Integer integer = set.get(c - 'a');
                if(integer == null){
                    set.put(c-'a',1);
                }else{
                    set.put(c-'a',++integer);
                }
            }
            for (char c : chars1) {
                if(set.containsKey(c-'a')){
                    Integer integer = set.get(c - 'a');
                    integer--;
                    set.put(c-'a',integer);
                }
            }
            for (Integer value : set.values()) {
                if(value>0){
                    return false;
                }
            }
            return true;
        }
        //使用数组
        public boolean canConstruct1(String ransomNote, String magazine) {
            int[] a = new int[26];
            for (char c : magazine.toCharArray()) {
                a[c-'a']++;
            }
            for (char c : ransomNote.toCharArray()) {
                if(a[c-'a']==0){
                    return false;
                }
                a[c-'a']--;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.canConstruct("bg", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj");
        System.out.println(b);
    }
}
