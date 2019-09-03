package com.test.algorithm.dichotomy;

/**
 * TODO 374. 猜数字大小
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 * 示例 :
 * <p>
 * 输入: n = 10, pick = 6
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class GuessNumberHigherOrLower {
    /* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
    static class GuessGame {
        int pick = 0;
        int guess(int num) {
            return Integer.compare(pick,num);
        }

        public void setPick(int pick) {
            this.pick = pick;
        }
    }

    static class Solution extends GuessGame {
        @Override
        public void setPick(int pick) {
            super.setPick(pick);
        }
        //二分法
        public int guessNumber(int n) {
            int first = 1;
            int last = n;
            while (first <= last) {
                int mid = first + (last - first) / 2;
                int res = guess(mid);
                if (res < 0) {
                    last = mid - 1;
                } else if (res > 0) {
                    first = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
        //三分法
        public int guessNumber3(int n) {
            int first = 1;
            int last = n;
            while (first <= last) {
                int mid1 = first + (last - first) / 3;
                int mid2 = last - (last - first) / 3;
                int res1 = guess(mid1);
                int res2 = guess(mid2);
                if(res1 == 0){
                    return mid1;
                }else if(res2 == 0){
                    return mid2;
                }else if(res1 < 0){
                    last = mid1 - 1;
                }else if(res2 > 0){
                    first = mid2 + 1;
                }else{
                    first = mid1 + 1;
                    last = mid2 - 1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.setPick(6);
        System.out.println(solution.guessNumber3(10));

    }
}
