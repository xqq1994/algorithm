package com.test.algorithm.array;
/**
 *@Description: 744. 寻找比目标字母大的最小字母
 * 给定一个只包含小写字母的有序数组letters 和一个目标字母 target，寻找有序数组里面比目标字母大的最小字母。
 *
 * 数组里字母的顺序是循环的。举个例子，如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。
 *
 * 示例:
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "a"
 * 输出: "c"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "c"
 * 输出: "f"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "d"
 * 输出: "f"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "g"
 * 输出: "j"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "j"
 * 输出: "c"
 *
 * 输入:
 * letters = ["c", "f", "j"]
 * target = "k"
 * 输出: "c"
 * 注:
 *
 * letters长度范围在[2, 10000]区间内。
 * letters 仅由小写字母组成，最少包含两个不同的字母。
 * 目标字母target 是一个小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/10/24
 */
public class FindSmallestLetterGreaterThanTarget {
    static class Solution {
        //直接暴力就完事了
        public char nextGreatestLetter(char[] letters, char target) {
            for (int i = 0; i < letters.length; i++) {
                if(letters[i] == target){
                    for (; i < letters.length; i++) {
                        if(letters[i] != target){
                            return letters[i];
                        }
                    }
                }else if(letters[i] > target){
                    return letters[i];
                }
            }
            return letters[0];
        }
        //二分法
        //循环结束时 l 指向数组中比目标字母大的最小字母，如果目标字母比所有字母都大，则 l = letters.size()，l mod letters.size() = 0，指向数组第一个字母。
        public char nextGreatestLetter1(char[] letters, char target) {
            int len = letters.length;
            int l = 0;
            int r = len - 1;
            while (l <= r){
                int mid = (l + r) / 2;
                if(target < letters[mid]){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }
            return letters[l % len];
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().nextGreatestLetter1(new char[]{'c', 'f', 'j'}, 'c'));
    }
}
