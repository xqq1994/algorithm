package com.test.algorithm.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *  
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 *
 * @Date 9:09 2019/8/5
 * @Param
 * @return
 */
public class FirstUniqueCharacterInAString {
    static class Solution {
        public int firstUniqChar(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c,map.getOrDefault(c,0)+1);
            }
            for (int i = 0; i < s.length(); i++) {
                if(map.get(s.charAt(i))==1){
                    return i;
                }
            }
            return -1;
        }
        //牛逼写法
        public int firstUniqChar1(String s) {
            int index = -1;
            //反过来，只有26个字符
            for (char ch = 'a'; ch <= 'z'; ch++) {
                int beginIndex = s.indexOf(ch);
                // 从头开始的位置是否等于结束位置，相等说明只有一个，
                if (beginIndex != -1 && beginIndex == s.lastIndexOf(ch)) {
                    //取小的，越小代表越前。
                    index = (index == -1 || index > beginIndex) ? beginIndex : index;
                }
            }
            return index;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().firstUniqChar1("leetcode"));
    }
}
