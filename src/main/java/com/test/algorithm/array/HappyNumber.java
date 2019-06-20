package com.test.algorithm.array;


import java.util.HashSet;
import java.util.Set;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 202. 快乐数
 * @Date 9:28 2019/6/20
 * @Param
 * 编写一个算法来判断一个数是不是“快乐数”。
 *
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 *
 * 示例: 
 *
 * 输入: 19
 * 输出: true
 * 解释:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @return
 */
public class HappyNumber {

    class Solution {
        Set<Integer> set = new HashSet<Integer>();
        public boolean isHappy(int n) {
            if(set.contains(n)){
                return false;
            }
            set.add(n);
            int b = 0;
            String s = String.valueOf(n);
            for (int i = 0; i < s.length(); i++) {
                int a = s.charAt(i) - '0';
                b += a * a;
            }
            if(b == 1){
                return true;
            }else{
                return isHappy(b);
            }
        }
    }
    //自己实现的快乐数
    class Solution1 {
        Set<Integer> set = new HashSet<Integer>();
        public boolean isHappy(int n) {
            if(set.contains(n)){
                return false;
            }
            set.add(n);
            int length = (n + "").length();
            int[] a = new int[length];
            for (int i = 0; i < a.length ; i++) {
                a[i] = n % 10;
                n = n / 10;
            }
            int b = 0;
            for (int i = 0; i < a.length; i++) {
                b += a[i]*a[i];
            }
            if(b == 1){
                return true;
            }else{
                return isHappy(b);
            }
        }

    }
}
