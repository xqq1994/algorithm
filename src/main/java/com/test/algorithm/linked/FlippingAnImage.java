package com.test.algorithm.linked;
/**
 * @Author xiaoqiangqiang
 * @Description //TODO 832 翻转图像
 * @Date 9:01 2019/5/30
 * @Param
 * @return
 */
public class FlippingAnImage {
    class Solution {
        public int[][] flipAndInvertImage(int[][] A) {
            for (int i = 0; i < A.length; i++) {
                int[] old = A[i];
                int[] newO = new int[old.length];
                for (int j = old.length-1; j >= 0 ; j--) {
                    newO[old.length-1-j] = old[j]^1;
                }
                A[i] = newO;
            }
            return A;
        }
    }
}
