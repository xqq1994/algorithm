package com.test.algorithm.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Description: 986. 区间列表的交集
 * 给定两个由一些闭区间组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 *
 * 返回这两个区间列表的交集。
 *
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 输入：A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * 注意：输入和所需的输出都是区间对象组成的列表，而不是数组或列表。
 *  
 *
 * 提示：
 *
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interval-list-intersections
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/10/29
 */
public class IntervalListIntersections {
    static class Solution {
        public int[][] intervalIntersection(int[][] A, int[][] B) {
            if(A.length==0 || B.length == 0){
                return new int[0][0];
            }
            List<int[]> list = new ArrayList<>();
            int i = 0,j = 0;
            while (i < A.length && j < B.length){
                int lo = Math.max(A[i][0],B[j][0]);
                int hi = Math.min(A[i][1],B[j][1]);
                if(lo <= hi){
                    list.add(new int[]{lo,hi});
                }
                if(A[i][1] < B[j][1]){
                    i++;
                }else{
                    j++;
                }
            }
            int[][] ints = new int[list.size()][list.get(0).length];
            return list.toArray(ints);
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().intervalIntersection(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}})));
    }
}
