package com.test.algorithm.dp;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 9:55 2019/8/16
 * @Param
 * @return
 */
public class TrappingRainWater {
    static class Solution {
        //按照行
        public int trap(int[] height) {
            //找到最大值
            int max = 0;
            for (int i = 0; i < height.length; i++) {
                if (max < height[i]) {
                    max = height[i];
                }
            }
            int ans = 0;
            //标记是否要更新
            for (int i = 1; i <= max; i++) {
                boolean isStart = false;
                int temp = 0;
                for (int j = 0; j < height.length; j++) {
                    //高度大于等于i时则把temp赋值给ans，并temp清空
                    if (i <= height[j]) {
                        ans += temp;
                        temp = 0;
                        isStart = true;
                    } else if (isStart && i > height[j]) {
                        temp++;
                    }
                }
            }
            return ans;
        }

        //按照列
        public int trap1(int[] height) {
            //忽略下标0和最后一个
            int sum = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int maxLeft = 0;
                //找出当前列左边最大值
                for (int j = i - 1; j >= 0; j--) {
                    if (height[j] > maxLeft) {
                        maxLeft = height[j];
                    }
                }
                int maxRight = 0;
                //找出当前列右边最大
                for (int j = i + 1; j < height.length; j++) {
                    if (maxRight < height[j]) {
                        maxRight = height[j];
                    }
                }
                //找到两端最大值的最小值
                int min = Math.min(maxLeft, maxRight);
                //留的水=min-当前列大小
                if (height[i] < min) {
                    sum += min - height[i];
                }
            }
            return sum;
        }

        //参照列来进行动态规划
        //maxLeft[i] = Math.max(maxLeft[i-1],height[i-1]);
        public int trap2(int[] height) {
            int sum = 0;
            int[] maxLeft = new int[height.length];
            int[] maxRight = new int[height.length];
            for (int i = 1; i < height.length - 1; i++) {
                maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
            }
            for (int i = height.length - 2; i >= 0; i--) {
                maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
            }
            //忽略下标0和最后一个
            for (int i = 1; i < height.length - 1; i++) {
                int min = Math.min(maxLeft[i], maxRight[i]);
                //留的水=min-当前列大小
                if (height[i] < min) {
                    sum += min - height[i];
                }
            }
            return sum;
        }

        //参照列来进行动态规划改造1--优化空间复杂度去除maxLeft数组
        //maxLeft[i] = Math.max(maxLeft[i-1],height[i-1]);
        public int trap3(int[] height) {
            int sum = 0;
            int maxLeft = 0;
            int[] maxRight = new int[height.length];
            for (int i = height.length - 2; i >= 0; i--) {
                maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
            }
            //忽略下标0和最后一个
            for (int i = 1; i < height.length - 1; i++) {
                maxLeft = Math.max(maxLeft, height[i - 1]);
                int min = Math.min(maxLeft, maxRight[i]);
                //留的水=min-当前列大小
                if (height[i] < min) {
                    sum += min - height[i];
                }
            }
            return sum;
        }

        //参照列来进行动态规划改造2--优化空间复杂度去除maxLeft、maxRight数组
        //maxLeft[i] = Math.max(maxLeft[i-1],height[i-1]);
        public int trap4(int[] height) {
            int sum = 0;
            int maxLeft = 0;
            int maxRight = 0;
            int left = 1;
            int right = height.length - 2;
            //忽略下标0和最后一个
            for (int i = 1; i < height.length - 1; i++) {
                if (height[left - 1] < height[right + 1]) {
                    maxLeft = Math.max(maxLeft, height[left - 1]);
                    int min = maxLeft;
                    if (height[left] < min) {
                        sum += min - height[left];
                    }
                    left++;
                } else {
                    maxRight = Math.max(maxRight, height[right + 1]);
                    int min = maxRight;
                    if (height[right] < min) {
                        sum += min - height[right];
                    }
                    right--;
                }

            }
            return sum;
        }
    }

    public static void main(String[] args) {
        int[] ints = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new Solution().trap2(ints));
    }
}
