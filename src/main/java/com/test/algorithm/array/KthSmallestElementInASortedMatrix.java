package com.test.algorithm.array;

import java.util.ArrayList;
import java.util.Collections;

/**
 *@Description: 378. 有序矩阵中第K小的元素378. 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *@Author: xiaoqiangqiang
 *@date: 2019/9/19
 */
public class KthSmallestElementInASortedMatrix {
    static class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    list.add(matrix[i][j]);
                }
            }
            Collections.sort(list);
            return list.get(k-1);
        }
        //每次的起始位置都是矩阵左下角的位置，如果当前位置即matrix[i][j] <= mid，
        // 那么说明当前位置上面的元素都小于当前元素，也就是有 i + 1 个元素小于等于当前元素，
        // 也就是总共有 i + 1 个元素小于等于 mid。比如题目的例子
        //[ [1，5，9]，[10，11，13]，[12，13，15] ]
        //要找排位第八的元素，首先 lo = 1，hi = 15，那么 mid = 8。
        // 明显matrix[i][j] = 12 > mid，往上一行找，matrix[i][j] = 10 > mid，
        // 再往上一行找，matrix[i][j] = 1 <= mid，cnt += 1，往右找，matrix[i][j] = 5 <= mid，cnt += 1，
        // 继续往右，matrix[i][j] > mid，停止。这时 cnt = 2，说明这个矩阵总共有2个元素小于等于8，而目标是要找到第八个，
        // 所以需要往后半段去找，也就是 lo = mid + 1 重复以上步骤去后半段找到目标排名的元素。
        public int kthSmallest1(int[][] matrix, int k) {
            int m = matrix.length, n = matrix[0].length;
            int lo = matrix[0][0], hi = matrix[m-1][n-1];
            while (lo <= hi) {
                int cnt = 0, mid = lo + (hi - lo) / 2;
                int i = m - 1, j = 0;
                while (i >= 0 && j < n) {
                    if (matrix[i][j] <= mid) {
                        cnt += i + 1;
                        j++;
                    } else {
                        i--;
                    }
                }
                if (cnt < k) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return lo;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kthSmallest1(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}},8));
    }
}
