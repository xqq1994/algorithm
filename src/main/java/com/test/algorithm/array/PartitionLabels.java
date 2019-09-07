package com.test.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: S = "ababcbacadefegdehijhklij"
 * 输出: [9,7,8]
 * 解释:
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 注意:
 * <p>
 * S的长度在[1, 500]之间。
 * S只包含小写字母'a'到'z'。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class PartitionLabels {
    static class Solution {
        public List<Integer> partitionLabels(String S) {
            ArrayList<Integer> list = new ArrayList<>();
            //存放26个字母最后一次在字符串中出现的位置
            int[] cache = new int[26];
            for (int i = 0; i < S.length(); i++) {
                cache[S.charAt(i) - 'a'] = i;
            }
            //preIndex 表示上个片段的右端点
            int preIndex = -1;
            //maxIndex表示当前遍历的字符最后出现位置的最大值
            int maxIndex = 0;
            for (int i = 0; i < S.length(); i++) {
                int index = cache[S.charAt(i) - 'a'];
                //更新区间的右端点, 向右延展
                if (index > maxIndex) {
                    maxIndex = index;
                }
                //如果出现当前位置i等于当前所遍历的字符最后出现位置的最大值
                if (i == maxIndex) {
                    list.add(maxIndex - preIndex);
                    //保存当前右端点
                    preIndex = maxIndex;
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().partitionLabels("ababcbacadefegdehijhklij"));
    }
}
