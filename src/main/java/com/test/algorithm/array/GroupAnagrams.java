package com.test.algorithm.array;

import java.util.*;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 9:14 2019/7/16
 * @Param
 * @return 
 */
public class GroupAnagrams {
    class Solution {
        //算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。
        //
        //利用这个，我们把每个字符串都映射到一个正数上。
        //
        //用一个数组存储质数 prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103}。
        //
        //然后每个字符串的字符减去 ' a ' ，然后取到 prime 中对应的质数。把它们累乘。
        //
        //例如 abc ，就对应 'a' - 'a'， 'b' - 'a'， 'c' - 'a'，即 0, 1, 2，也就是对应素数 2 3 5，然后相乘 2 * 3 * 5 = 30，就把 "abc" 映射到了 30。
        //时间复杂度：O（n * K）O（n∗K），K 是字符串的最长长度。
        //
        //空间复杂度：O（NK）O（NK），用来存储结果。
        //
        //这个解法时间复杂度，较解法二有提升，但是有一定的局限性，因为求 key 的时候用的是累乘，可能会造成溢出，超出 int 所能表示的数字。
        //
        public List<List<String>> groupAnagrams(String[] strs) {
            int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
            ArrayList<List<String>> list1 = new ArrayList<>();
            Map<Integer, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                int a = 1;
                for (char aChar : chars) {
                    a+=prime[aChar-'a'];
                }
                if (map.containsKey(a)) {
                    map.get(a).add(str);
                }else{
                    List<String> strings = new ArrayList<>();
                    strings.add(str);
                    map.put(a,strings);
                }
            }
            return new ArrayList<List<String>>(map.values());
        }
        //我们将每个字符串按照字母顺序排序，这样的话就可以把 eat，tea，ate 都映射到 aet。其他的类似。
        public List<List<String>> groupAnagrams1(String[] strs) {
            HashMap<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] chars = s.toCharArray();
                Arrays.sort(chars);
                String s1 = String.valueOf(chars);
                if (map.containsKey(s1)) {
                    map.get(s1).add(s);
                }else{
                    ArrayList<String> list = new ArrayList<>();
                    list.add(s);
                    map.put(s1,list);
                }
            }
            return new ArrayList<>(map.values());
        }

    }
}
