package com.test.algorithm.other;

/**
 * TODO 372. 超级次方
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 2, b = [3]
 * 输出: 8
 * 示例 2:
 * <p>
 * 输入: a = 2, b = [1,0]
 * 输出: 1024
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-pow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class SuperPow {
    static class Solution {
        //372超级次方
        public int superPow(int a, int[] b) {
            int c = 1337;
            int exp = 0;
//            int phi = euler(c);
            //同余
            for (int i = 0; i < b.length; i++) {
                exp = (exp * 10 + b[i]) % 1140;
            }
            return qucikPow(a % 1337, exp, 1337);
        }

        //快速幂计算
        public int qucikPow(int a, int b, int c) {
            int ans = 1;
            while (b > 0) {
                if ((b & 1) == 1) {
                    ans = (ans * a) % c;
                }
                b >>= 1;
                a = (a * a) % c;
            }
            return ans;
        }

        //欧拉函数
        public int euler(int n) {
            int ret = n;
            for (int i = 2; i * i < n; i++) {
                if (n % i == 0) {//n的质因数
                    ret = ret / n * (n - 1); //欧拉函数公式
                    while (n % i == 0) {//去掉质因数i
                        n /= i;
                    }
                }
            }
            if (n > 1) {//n本来就是质数 f(n) = n-1;
                ret = ret / n * (n - 1);
            }
            return ret;
        }
        //根本算不出来，python的好处体现出来了
        public int superPow1(int a, int[] b) {
            int c = 0;
            for (int i = 0; i < b.length; i++) {
                c = c*10 + b[i];
            }
            double pow = Math.pow(a, c);

            return (int)pow % 1337;
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().superPow1(2147483647, new int[]{2,0,0}));
    }
}
