package com.test.algorithm.other;

/**
 * @Description: 223. 矩形面积
 * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 * <p>
 * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: -3, 0, 3, 4, 0, -1, 9, 2
 * 输出: 45
 * 说明: 假设矩形面积不会超出 int 的范围。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rectangle-area
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/9/10
 */
public class RectangleArea {
    static class Solution {

        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            if (E >= C || G <= A || H <= B || F >= D) {
                return (D - B) * (C - A) + (H - F) * (G - E);  //没有重合情况
            }
            int mAreaWidth = Math.min(C, G) - Math.max(E, A);  //重合部分的宽
            int mAreaHeight = Math.min(H, D) - Math.max(B, F); //重合部分的高
            int sumArea = (D - B) * (C - A) + (H - F) * (G - E);   //总面积
            int mArea = mAreaWidth * mAreaHeight;
            return sumArea - mArea;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().computeArea(-2,
                -2,
                2,
                2,
                -3,
                -3,
                3,
                -1));
    }
}
