package com.test.algorithm.array;

/**
 * TODO 551. 学生出勤记录 I
 * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
 * <p>
 * 'A' : Absent，缺勤
 * 'L' : Late，迟到
 * 'P' : Present，到场
 * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
 * <p>
 * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "PPALLP"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "PPALLL"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class StudentAttendanceRecordI {
    static class Solution {
        //自己写的
        public boolean checkRecord(String s) {
            char[] chars = s.toCharArray();
            int a = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'A') {
                    a++;
                }
                if (i + 2 < chars.length && chars[i] == 'L' && chars[i] == chars[i + 1] && chars[i + 1] == chars[i + 2]) {
                    return false;
                }
            }
            if (a > 1) {
                return false;
            }
            return true;
        }

        //一行
        public boolean checkRecord1(String s) {
            return s.indexOf("A") == s.lastIndexOf("A") && !s.contains("LLL");
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean ppaall = solution.checkRecord("PPALLP");
        System.out.println(ppaall);
    }
}
