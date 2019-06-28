package com.test.algorithm.bfsordfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO 22. 括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class GenerateParentheses {
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            generateOneByOne("",result,n,n);
            return result;
        }
        public void generateOneByOne(String subList,List<String> result,int left,int right){
            if(left == 0 && right == 0){
                result.add(subList);
                return;
            }
            if(left > 0){
                generateOneByOne(subList+"(",result,left - 1,right);
            }
            if(right > left){
                generateOneByOne(subList + ")",result,left,right -1);
            }
        }
    }
}
