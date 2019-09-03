package com.test.algorithm.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *@Description: 677. 键值映射
 *实现一个 MapSum 类里的两个方法，insert 和 sum。
 *
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 *
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * 示例 1:
 *
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/9/3
 */
public class MapSumPairs {
    static class MapSum {
        Map<String, Integer> map;

        /** Initialize your data structure here. */
        public MapSum() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key,val);
        }

        public int sum(String prefix) {
            int a = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if(key.startsWith(prefix,0)){
                    a += value;
                }
            }
            return a;
        }
    }
    static class MapSumTrie {
        class TrieNode {
            public int val;
            public TreeMap<Character,TrieNode> next = new TreeMap();
            public TrieNode() {
                this(0);
            }

            public TrieNode(int val) {
                next = new TreeMap<>();
                this.val = val;
            }
        }

        private TrieNode root;


        /** Initialize your data structure here. */
        public MapSumTrie() {
            root = new TrieNode();
        }

        public void insert(String key, int val) {
            TrieNode ws = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if(ws.next.get(c) == null){
                    ws.next.put(c,new TrieNode());
                }
                ws = ws.next.get(c);
            }
            ws.val = val;
        }

        public int sum(String prefix) {
            TrieNode ws = this.root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if(ws.next.get(c) == null){
                    return 0;
                }
                ws = ws.next.get(c);
            }
            return sum(ws);
        }

        public int sum(TrieNode ws){
            int val = ws.val;
            Set<Character> characters = ws.next.keySet();
            for (Character c : characters) {
                val += sum(ws.next.get(c));
            }
            return val;
        }
    }

    public static void main(String[] args) {
        MapSumTrie mapSum = new MapSumTrie();
        mapSum.insert("apply",3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app",2);
        System.out.println(mapSum.sum("ap"));


    }
}
