package com.test.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 846. 一手顺子
 * 爱丽丝有一手（hand）由整数数组给定的牌。 
 *
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 *
 * 如果她可以完成分组就返回 true，否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
 * 输出：true
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 *
 * 输入：hand = [1,2,3,4,5], W = 4
 * 输出：false
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 *  
 *
 * 提示：
 *
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hand-of-straights
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 9:02 2019/8/26
 * @Param
 * @return
 */
public class HandOfStraights {
    static class Solution {
        public boolean isNStraightHand(int[] hand, int W) {
            if(hand == null || hand.length == 0 || hand.length % W !=0) {
                return false;
            }
            Arrays.sort(hand);
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : hand) {
                map.put(i,map.getOrDefault(i,0)+1);
            }
            for (int h : hand) {
                if(map.get(h) > 0){
                    for (int i = 0; i < W; i++) {
                        if (map.getOrDefault(i + h, 0) > 0) {
                            map.put(i + h, map.get(i + h) - 1);
                        } else {
                            return false;
                        }
                    }
                }

            }
            return true;
        }
        public boolean isNStraightHand1(int[] hand, int W) {
            if(hand.length % W != 0){
                return false;
            }
            int[] ans = new int[W];
            for (int i : hand) {
                ans[i % W]++;
            }
            for (int i = 0; i < W - 1; i++) {
                if(ans[i] != ans[i + 1]){
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
    }
}
