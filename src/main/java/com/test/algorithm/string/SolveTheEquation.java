package com.test.algorithm.string;

/**
 * @Description: 640. 求解方程
 * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
 * <p>
 * 如果方程没有解，请返回“No solution”。
 * <p>
 * 如果方程有无限解，则返回“Infinite solutions”。
 * <p>
 * 如果方程中只有一个解，要保证返回值 x 是一个整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 * <p>
 * 输入: "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 * <p>
 * 输入: "2x=x"
 * 输出: "x=0"
 * 示例 4:
 * <p>
 * 输入: "2x+3x-6x=x+2"
 * 输出: "x=-1"
 * 示例 5:
 * <p>
 * 输入: "x=x+2"
 * 输出: "No solution"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/solve-the-equation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/18
 */
public class SolveTheEquation {
    static class Solution {
        public String solveEquation(String equation) {
            String[] split = equation.split("=");
            String leftSplit = split[0];
            String rightSplit = split[1];
            int[] leftXAndNum = countXandNum(leftSplit);
            int[] rightXAndNum = countXandNum(rightSplit);
            //如果左边的X与右边的X相等
            if (leftXAndNum[0] == rightXAndNum[0]) {
                //且左边的数字和右边的数字相等则无限解
                if (leftXAndNum[1] == rightXAndNum[1]) {
                    return "Infinite solutions";
                } else {
                    //左右边的数字不相等则无解
                    return "No solution";
                }
            } else {
                return "x=" + (rightXAndNum[1] - leftXAndNum[1]) / (leftXAndNum[0] - rightXAndNum[0]);
            }
        }

        //x+5-3+x=6+x-2
        private int[] countXandNum(String part) {
            int[] res = new int[2];
            String[] split = part.split("-");
            int isNegative = 1;
            for (int i = 0; i < split.length; i++) {
                if (i != 0) {
                    isNegative = -1;
                }
                String[] split1 = split[i].split("\\+");
                for (int j = 0; j < split1.length; j++) {
                    if (split1[j].length() < 1) {
                        continue;
                    }
                    if (j != 0) {
                        isNegative = 1;
                    }
                    //判断字符串是否有x
                    if (split1[j].charAt(split1[j].length() - 1) == 'x') {
                        //有x则要判断是否x前面是否有倍数
                        if (split1[j].length() > 1) {
                            res[0] += isNegative * Integer.parseInt(split1[j].substring(0, split1[j].length() - 1));
                        } else {
                            res[0] += isNegative;
                        }
                    } else {
                        //没有x
                        res[1] += isNegative * Integer.parseInt(split1[j]);
                    }
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solveEquation("x=x+2"));
    }
}
