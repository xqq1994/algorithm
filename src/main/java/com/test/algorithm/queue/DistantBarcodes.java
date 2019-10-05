package com.test.algorithm.queue;

import java.util.*;

/**
 * TODO 1054. 距离相等的条形码
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * <p>
 * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 * 示例 2：
 * <p>
 * 输入：[1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distant-barcodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class DistantBarcodes {
    static class Solution {
        public int[] rearrangeBarcodes(int[] barcodes) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : barcodes) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort((o1, o2) -> o2.getValue() - o1.getValue());
            int index = 0;
            int len = barcodes.length;
            int key;
            int value;
            int[] res = new int[barcodes.length];
            for (Map.Entry<Integer, Integer> entry : list) {
                key = entry.getKey();
                value = entry.getValue();
                while (value > 0){
                    if(index >= len){
                        index = 1;
                    }
                    res[index] = key;
                    index += 2;
                    value--;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rearrangeBarcodes(new int[]{1, 1, 1, 2, 2, 2}));
    }
}
