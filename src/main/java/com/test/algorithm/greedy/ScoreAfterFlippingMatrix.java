package com.test.algorithm.greedy;
/**
 * TODO 861. 翻转矩阵后的得分
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 * 返回尽可能高的分数。
 *
 *  
 *
 * 示例：
 *
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/score-after-flipping-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class ScoreAfterFlippingMatrix {
    class Solution {
        //具体做法：我们怎么知道每一行是否需要翻转达到最优呢？
        // 很简单，只需要看最高位是否为1，即题目的A[r][0] == 1 ?等于1的时候不用翻转，0则需要翻转。
        // 我们假设数组长度为C，当最高为为1时，不管后面(C - 1)是1是0，这个数最小值至少为2^(C-1) 。而当最高位为0时，
        // 尽管后面全是1，最大也是2^(C - 1) - 1。所以当最高位为1时，行最优。
        //而列就比较简单了，假设一列有R个数，有count个1，当count < R - count时需要翻转。
        public int matrixScore(int[][] A) {
            int row = A.length, col = A[0].length;
            int ans = 0;
            for(int c = 0; c < col; ++c){
                int count = 0;
                for(int r = 0; r < row; ++r){
                    //行翻转加计算列1的个数
                    //虽然这里找的是0的个数，但并不影响
                    count += A[r][c] ^ A[r][0];
                }
                //列翻转
                if(count < row - count) count = row - count;
                ans += count * (1 << (col - c - 1));
            }
            return ans;
        }
    }
}
