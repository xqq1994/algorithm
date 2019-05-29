package com.test.algorithm.linked;
/**
 * @Author xiaoqiangqiang
 * @Description //TODO 141 环形链表
 * @Date 14:42 2019/5/29
 * @Param
 * @return 
 */
public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public boolean hasCycle(ListNode head) {
        //快慢指针
        ListNode slow = new ListNode(0);
        ListNode fast = new ListNode(0);
        slow.next = head;
        fast.next = head;
        while (head != null){
            if(head.next != null){
                ListNode tmp = slow.next;
                ListNode tmp1 = fast.next.next;
                if(tmp.val == tmp1.val){
                    return true;
                }
            }
        }
        return false;
    }
}
