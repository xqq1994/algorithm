package com.test.algorithm.trie;

import java.util.*;

/**
 * TODO 820. 单词的压缩编码
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 *
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
 *
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
 *
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 *  
 *
 * 示例：
 *
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class ShortEncodingOfWords {
    class Solution {
        public int minimumLengthEncoding(String[] words) {
            Set<String> good = new HashSet(Arrays.asList(words));
            for (String word: words) {
                for (int k = 1; k < word.length(); ++k)
                    good.remove(word.substring(k));
            }

            int ans = 0;
            for (String word: good)
                ans += word.length() + 1;
            return ans;
        }
    }
    class Solution1 {
        public int minimumLengthEncoding(String[] words) {
            TrieNode trie = new TrieNode();
            Map<TrieNode, Integer> nodes = new HashMap();

            for (int i = 0; i < words.length; ++i) {
                String word = words[i];
                TrieNode cur = trie;
                for (int j = word.length() - 1; j >= 0; --j)
                    cur = cur.get(word.charAt(j));
                nodes.put(cur, i);
            }

            int ans = 0;
            for (TrieNode node: nodes.keySet()) {
                if (node.count == 0)
                    ans += words[nodes.get(node)].length() + 1;
            }
            return ans;

        }
    }

    class TrieNode {
        TrieNode[] children;
        int count;
        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
        public TrieNode get(char c) {
            if (children[c-'a'] == null) {
                children[c-'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }
}
