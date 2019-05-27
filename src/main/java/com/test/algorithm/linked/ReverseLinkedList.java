package com.test.algorithm.linked;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @Author xiaoqiangqiang
 * @Description  //TODO 206 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
 * @Date 11:11 2019/5/27
 * @Param
 * @return
 */
public class ReverseLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null){
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }
    }
}
