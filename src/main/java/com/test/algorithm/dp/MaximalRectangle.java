package com.test.algorithm.dp;

import java.util.Arrays;

/**
 * @Description: 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * ['1','0','1','0','0'],
 * ['1','0','1','1','1'],
 * ['1','1','1','1','1'],
 * ['1','0','0','1','0']
 * ]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/10/8
 */
public class MaximalRectangle {
    static class Solution {
        //我们知道，最大矩形必为用这种方式构建的矩形之一。
        //
        //给定一个最大矩形，其高为 h， 左边界 l，右边界 r，在矩形的底边，区间 [l, r]内必然存在一点，其上连续1的个数（高度）<=h。若该点存在，则由于边界内的高度必能容纳h，以上述方法定义的矩形会向上延伸到高度h，再左右扩展到边界 [l, r] ，于是该矩形就是最大矩形。
        //
        //若不存在这样的点，则由于[l, r]内所有的高度均大于h，可以通过延伸高度来生成更大的矩形，因此该矩形不可能最大。
        //
        //综上，对于每个点，只需要计算h， l，和 r - 矩形的高，左边界和右边界。
        //
        //使用动态规划，我们可以在线性时间内用上一行每个点的 h，l，和 r 计算出下一行每个点的的h，l，和r。
        //
        //算法
        //
        //给定一行 matrix[i]，我们通过定义三个数组height，left，和 right来记录每个点的h，l，和 r。height[j] 对应matrix[i][j]的高，以此类推。
        //
        //问题转化为如何更新每个数组。
        //
        //Height:
        //
        //这个比较容易。 h 的定义是从该点出发连续的1的个数。我们从方法二中已经学会了在一行中计算的方法:
        //
        //row[j] = row[j - 1] + 1 if row[j] == '1'
        //只需要一点改动即可:
        //
        //new_height[j] = old_height[j] + 1 if row[j] == '1' else 0
        //Left:
        //
        //考虑哪些因素会导致矩形左边界的改变。由于当前行之上的全部0已经考虑在当前版本的left中，唯一能影响left就是在当前行遇到0。
        //
        //因此我们可以定义:
        //
        //new_left[j] = max(old_left[j], cur_left)
        //cur_left是我们遇到的最右边的0的序号加1。当我们将矩形向左 “扩展” ，我们知道，不能超过该点，否则会遇到0。
        //
        //Right:
        //
        //我们可以沿用 left 的思路，定义:
        //
        //new_right[j] = min(old_right[j], cur_right)
        //cur_right 是我们遇到的最左边的0的序号。简便起见，我们不把 cur_right 减去1 (就像我们给cur_left加上1那样) ，这样我们就可以用height[j] * (right[j] - left[j]) 而非height[j] * (right[j] + 1 - left[j])来计算矩形面积。
        //
        //这意味着， 严格地说 ，矩形的底边由半开半闭区间[l, r) 决定，而非闭区间 [l, r]，且 right比右边界大1。尽管不这样做算法也可以正确运行，但这样会让计算看起来更简洁。
        //
        //注意，为了正确的记录 cur_right，我们需要从右向左迭代。因此，更新right时需要从右向左。
        //
        //一旦left，right，和 height数组能够正确更新，我们就只需要计算每个矩形的面积。
        //
        //由于我们知道矩形 j的边界和高，可以简单地用height[j] * (right[j] - left[j])来计算面积，若j的面积 大于max_area，则更新之。
        //
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            //将左边初始化为最左边的边界
            int[] left = new int[n];
            int[] right = new int[n];
            int[] height = new int[n];
            //将右初始化为最右边的边界
            Arrays.fill(right, n);

            int maxarea = 0;
            for (int i = 0; i < m; i++) {
                int curLeft = 0, curRight = n;
                // update height
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        height[j]++;
                    } else {
                        height[j] = 0;
                    }
                }
                // update left
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        left[j] = Math.max(left[j], curLeft);
                    } else {
                        left[j] = 0;
                        curLeft = j + 1;
                    }
                }
                // update right
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') {
                        right[j] = Math.min(right[j], curRight);
                    } else {
                        right[j] = n;
                        curRight = j;
                    }
                }
                // update area
                for (int j = 0; j < n; j++) {
                    maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
                }
            }
            return maxarea;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
    }
}