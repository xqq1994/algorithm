package com.test.algorithm.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 605. 种花问题
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 * <p>
 * 示例 1:
 * <p>
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * 注意:
 * <p>
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 * @Date 8:40 2019/7/26
 * @Param
 * @return
 */
public class CanPlaceFlowers {
    static class Solution {
        //解题思路：遍历flowerbed[]数组，依次判断各位置是否适合种花，
        // 判断的标准是当前位置为0，且前一位置为0或其下标为-1，且后一位置为0或其下标为flowerbed.length。若判断为真，则修改flowerbed[]数组当前位置为1，n--。最后若n<=0,则能种下所有花。
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0 && (i - 1 == -1 || flowerbed[i - 1] == 0) && (i + 1 == flowerbed.length || flowerbed[i + 1] == 0)) {
                    n--;
                    flowerbed[i] = 1;
                }
            }
            return n <= 0;
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] flowerbed = stringToIntegerArray(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            boolean ret = new Solution().canPlaceFlowers(flowerbed, n);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}
