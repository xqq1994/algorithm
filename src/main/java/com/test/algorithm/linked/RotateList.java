package com.test.algorithm.linked;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO 61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class RotateList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            if(head == null || k == 0){
                return head;
            }
            ListNode cur = head;
            ListNode tail = null;
            int len = 1;
            //获取链表节点数
            while (cur.next != null) {
                cur = cur.next;
                len++;
            }
            //需要循环的次数
            int loop = len - (k % len);
            //改成循环链表
            tail = cur;
            cur.next = head;
            cur = head;
            //开始向右循环
            for (int i = 0; i < loop; i++) {
                cur = cur.next;
                tail = tail.next;
            }
            //改成单链表
            tail.next = null;
            return cur;
        }
        public static int[] stringToIntegerArray(String input) {
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            if (input.length() == 0) {
                return new int[0];
            }

            String[] parts = input.split(",");
            int[] output = new int[parts.length];
            for(int index = 0; index < parts.length; index++) {
                String part = parts[index].trim();
                output[index] = Integer.parseInt(part);
            }
            return output;
        }

        public static ListNode stringToListNode(String input) {
            // Generate array from the input
            int[] nodeValues = stringToIntegerArray(input);

            // Now convert that list into linked list
            ListNode dummyRoot = new ListNode(0);
            ListNode ptr = dummyRoot;
            for(int item : nodeValues) {
                ptr.next = new ListNode(item);
                ptr = ptr.next;
            }
            return dummyRoot.next;
        }

        public static String listNodeToString(ListNode node) {
            if (node == null) {
                return "[]";
            }

            String result = "";
            while (node != null) {
                result += Integer.toString(node.val) + ", ";
                node = node.next;
            }
            return "[" + result.substring(0, result.length() - 2) + "]";
        }

        public static void main(String[] args) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                ListNode head = stringToListNode(line);
                line = in.readLine();
                int k = Integer.parseInt(line);

                ListNode ret = new Solution().rotateRight(head, k);

                String out = listNodeToString(ret);

                System.out.print(out);
            }
        }
    }
}
