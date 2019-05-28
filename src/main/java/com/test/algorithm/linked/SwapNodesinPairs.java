package com.test.algorithm.linked;


/**
 *24 两两交换链表中的节点
 * **/
public class SwapNodesinPairs {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    public static ListNode swapPairs(ListNode head) {
//        if(head == null || head.next == null){
//            return head;
//        }
//        ListNode next = head.next;
//        head.next = swapPairs(next.next);
//        next.next = head;
//        return next;
        ListNode dump = new ListNode(0);
        ListNode p = dump;
        while (head != null){
            if(head.next != null){
                ListNode next = head.next;
                p.next = next;
                head.next = head.next.next;
                next.next = head;
                head = head.next;
                p = p.next.next;
            }else{
                p.next = head;
                head = null;
            }
        }
        return dump.next;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        ListNode listNode1 = swapPairs(listNode);
        System.out.println(listNode1);

    }

}
