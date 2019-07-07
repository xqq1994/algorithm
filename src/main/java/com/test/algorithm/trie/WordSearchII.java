package com.test.algorithm.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO 212. 单词搜索 II
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * <p>
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * <p>
 * 提示:
 * <p>
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class WordSearchII {
    class Solution {
        Set<String> res = new HashSet<>();
        public List<String> findWords(char[][] board, String[] words) {
            Trie trie = new Trie();
            for (int i = 0; i < words.length; i++) {
                trie.insert(words[i]);
            }
            int a = board.length;
            int b = board[0].length;
            boolean[][] visisted = new boolean[a][b];
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    dfs(board, visisted, "", i, j, trie);
                }
            }
            return new ArrayList<>(res);
        }

        public void dfs(char[][] board, boolean[][] visisted, String s, int i, int j, Trie trie) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
            if (visisted[i][j]) return;
            s += board[i][j];
            if (!trie.startsWith(s)) return;
            if (trie.search(s)) {
                res.add(s);
            }
            visisted[i][j] = true;
            dfs(board, visisted, s, i - 1, j, trie);
            dfs(board, visisted, s, i + 1, j, trie);
            dfs(board, visisted, s, i, j - 1, trie);
            dfs(board, visisted, s, i, j + 1, trie);
            visisted[i][j] = false;
        }
    }
}
