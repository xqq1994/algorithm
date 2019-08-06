package com.test.algorithm.bfsordfs;
/**
 * @Author xiaoqiangqiang
 * @Description //TODO 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 * @Date 9:18 2019/8/6
 * @Param
 * @return
 */
public class InterleavingString {
    class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            return is_interleave(s1,0,s2,0,"",s3);
        }
        public boolean is_interleave(String s1, int i, String s2, int j, String res, String s3){
            if(res.equals(s3) && i==s1.length() && j==s2.length()){
                return true;
            }
            boolean ans = false;
            if(i < s1.length()){
                ans |= is_interleave(s1,i+1,s2,j,res+s1.charAt(i),s3);
            }
            if(j < s2.length()){
                ans |= is_interleave(s1,i,s2,j+1,res+s2.charAt(j),s3);
            }
            return ans;
        }
        //二维数组
        public boolean isInterleave1(String s1, String s2, String s3) {
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = true;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[s1.length()][s2.length()];
        }
        //一维数组
        public boolean isInterleave2(String s1, String s2, String s3) {
            if (s3.length() != s1.length() + s2.length()) {
                return false;
            }
            boolean dp[] = new boolean[s2.length() + 1];
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 && j == 0) {
                        dp[j] = true;
                    } else if (i == 0) {
                        dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else if (j == 0) {
                        dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                    }
                }
            }
            return dp[s2.length()];
        }
    }
}
