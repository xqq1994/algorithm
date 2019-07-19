package com.test.algorithm.array;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 168. Excel表列名称
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如，
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "A"
 * 示例 2:
 * <p>
 * 输入: 28
 * 输出: "AB"
 * 示例 3:
 * <p>
 * 输入: 701
 * 输出: "ZY"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date 10:01 2019/7/19
 * @Param
 * @return
 */
public class ExcelSheetColumnTitle {
    static class Solution {
        public String convertToTitle(int n) {
            String[] aa = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            StringBuilder result = new StringBuilder();
            while (n > 26) {
                int temp;
                temp = n % 26;
                if(temp != 0){
                    result.insert(0, aa[temp - 1]);
                }else{
                    result.insert(0, "Z");
                    n -=26;
                }
                n = n / 26;
            }
            if (n != 0) {
                result.insert(0, aa[n - 1]);
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.convertToTitle(702);
        System.out.println(s);

    }
}
