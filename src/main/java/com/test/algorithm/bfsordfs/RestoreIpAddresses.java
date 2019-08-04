package com.test.algorithm.bfsordfs;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 **/
public class RestoreIpAddresses {
    static class Solution {
        private void backtracking(String s, int pos, List<String> cur, List<String> ans) {
            if (cur.size() >= 4) {
                if (pos == s.length()) ans.add(String.join(".", cur));
                return;
            }
            // 分割得到ip地址的一段后，下一段只能在长度1-3范围内选择
            for (int i = 1; i <= 3; i++) {
                if (pos + i > s.length()) break;
                String segment = s.substring(pos, pos + i);
                // 剪枝条件：不能以0开头，不能大于255
                if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255))
                    continue;
                cur.add(segment);
                // 注意此处传的参数
                backtracking(s, pos + i, cur, ans);
                System.out.println(JSON.toJSONString(cur));
                cur.remove(cur.size() - 1);
            }
        }

        public List<String> restoreIpAddresses(String s) {
            List<String> ans = new ArrayList<>();
            backtracking(s, 0, new ArrayList<>(), ans);
            return ans;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().restoreIpAddresses("25525511135"));
    }
}
