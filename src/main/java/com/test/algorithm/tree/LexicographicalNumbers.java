package com.test.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 386. 字典序排数
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * <p>
 * 例如，
 * <p>
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * <p>
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/10/16
 */
public class LexicographicalNumbers {
    static class Solution {
        private List<Integer> resList = new ArrayList<>();

        public List<Integer> lexicalOrder(int n) {
            if (n < 10) {
                for (int i = 1; i <= n; ++i)
                    resList.add(i);
                return resList;
            }
            process(1, n);
            return resList;
        }

        private void process(int m, int n) {
            if (m > n) return;
            for (int i = 0; i < 10 && m + i <= n; ++i) {
                if (m == 1 && i == 9) break;
                resList.add(m + i);
                process((m + i) * 10, n);
            }
        }

    }

    static class Solution1 {
        public List<Integer> lexicalOrder(int n) {
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                dfs(n, i, res);
            }
            return res;
        }

        private static void dfs(int n, int target, List<Integer> list) {
            if (target > n) return;
            list.add(target);
            target *= 10;
            for (int i = 0; i < 10; i++) {
                dfs(n, target + i, list);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution1().lexicalOrder(13));
    }
}
