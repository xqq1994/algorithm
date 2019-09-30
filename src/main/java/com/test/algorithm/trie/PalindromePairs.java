package com.test.algorithm.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/30
 */
public class PalindromePairs {
    static class Solution {
        //数组里有空字符串，并且数组里还有自己就是回文的字符串，每出现一个可与空字符串组成两对。
        //如果自己的翻转后的字符串也在数组里，可以组成一对，注意翻转后不能是自己。
        //如果某个字符串能找到一个分割点，分割点前的部分是回文，后半部分翻转后也在数组里，可组成一对。
        //把3反过来，如果后部分是回文，前半部分翻转后在数组里，可组成一对。
        public List<List<Integer>> palindromePairs(String[] words) {
            List<List<Integer>> res = new ArrayList<>();
            if (words == null || words.length < 2) {
                return res;
            }

            HashMap<String, Integer> hm = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                hm.put(words[i], i);
            }

            for (int i = 0; i < words.length; i++) {
                //j是能到word[i].length()的
                for (int j = 0; j <= words[i].length(); j++) {
                    String left = words[i].substring(0, j);
                    String right = words[i].substring(j);
                    if (isPal(left)) {
                        String reverseRight = new StringBuilder(right).reverse().toString();
                        if (hm.containsKey(reverseRight) && hm.get(reverseRight) != i) {
                            List<Integer> item = new ArrayList<>();
                            item.add(hm.get(reverseRight));
                            item.add(i);
                            res.add(item);
                        }
                    }
                    if (isPal(right)) {
                        String reverseLeft = new StringBuilder(left).reverse().toString();
                        if (hm.containsKey(reverseLeft) && hm.get(reverseLeft) != i && right.length() != 0) {
                            List<Integer> item = new ArrayList<>();
                            item.add(i);
                            item.add(hm.get(reverseLeft));
                            res.add(item);
                        }
                    }
                }
            }
            return res;
        }

        //判断是否是回文
        public boolean isPal(String val) {
            char[] chars = val.toCharArray();
            int left = 0;
            int right = chars.length - 1;
            while (left < right) {
                if (chars[left++] != chars[right--]) {
                    return false;
                }
            }
            return true;
        }


    }

    public static void main(String[] args) {
        System.out.println(new Solution().palindromePairs(new String[]{"abcd","dcba","lls","s","sssll"}));
    }
}
