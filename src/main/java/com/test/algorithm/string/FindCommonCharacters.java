package com.test.algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/10/25
 */
public class FindCommonCharacters {
    static class Solution {
        public List<String> commonChars(String[] A) {
            List<String> list = new ArrayList<>();
            int[] res = new int[26];
            for (Character s : A[0].toCharArray()) {
                res[s - 'a']++;
            }
            for (int i = 1; i < A.length; i++) {
                int[] temp = new int[26];
                for (Character s : A[i].toCharArray()) {
                    temp[s - 'a']++;
                }
                for (int j = 0; j < 26; j++) {
                    res[j] = Math.min(res[j], temp[j]);
                }
            }
            for (int i = 0; i < res.length; i++) {
                if (res[i] > 0) {
                    for (int j = 0; j < res[i]; j++) {
                        list.add((char) (i + 'a') + "");
                    }
                }
            }
            return list;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().commonChars(new String[]{"bella", "label", "roller"}));
    }
}
