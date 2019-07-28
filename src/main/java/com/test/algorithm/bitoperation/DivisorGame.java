package com.test.algorithm.bitoperation;

/**
 * TODO 1025. 除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * <p>
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * <p>
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * <p>
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 1000
 **/
public class DivisorGame {
    static class Solution {
        //自己写的
        public boolean divisorGame(int N) {
            if (N == 1) return false;
            int a = 0;
            for (int i = 1; i < N; i++) {
                if (N % i == 0) {
                    N = N - i;
                    i = 0;
                    a++;
                }
            }
            return a % 2 != 0;
        }

        //数字N如果是奇数，它的约数必然都是奇数；若为偶数，则其约数可奇可偶。
        //无论N初始为多大的值，游戏最终只会进行到N=2时结束，那么谁轮到N=2时谁就会赢。
        //因为爱丽丝先手，N初始若为偶数，爱丽丝则只需一直选1，使鲍勃一直面临N为奇数的情况，这样爱丽丝稳赢； N初始若为奇数，那么爱丽丝第一次选完之后N必为偶数，那么鲍勃只需一直选1就会稳赢。
        public boolean divisorGame1(int N) {
            return N % 2 == 0;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.divisorGame(2);
        System.out.println(b);
    }
}
