package com.test.algorithm.bfsordfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO 934. 最短的桥
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 * <p>
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 * <p>
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-bridge
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class ShortestBridge {
    static class Solution {
        public int shortestBridge(int[][] A) {
            //find the first island, using dfs
            int m = A.length, n = A[0].length;
            boolean[][] visited = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<>(); //for bfs
            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            boolean found = false;
            for (int i = 0; i < m; i++) {
                if (found) break;
                for (int j = 0; j < n; j++) {
                    if (A[i][j] == 1) {
                        found = true;
                        dfs(A, i, j, visited, dirs, queue);
                        break;
                    }
                }
            }

            int count = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = queue.poll();
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;
                        if (A[x][y] == 1) return count;
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
                count++;
            }
            return -1;
        }

        private void dfs(int[][] A, int i, int j, boolean[][] visited, int[][] dirs, Queue<int[]> queue) {
            int m = A.length, n = A[0].length;
            if (i < 0 || i >= m || j < 0 || j >= n || A[i][j] == 0 || visited[i][j]) return;
            queue.offer(new int[]{i, j});
            visited[i][j] = true;
            for (int[] dir : dirs) {
                dfs(A, i + dir[0], j + dir[1], visited, dirs, queue);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestBridge(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
    }
}
