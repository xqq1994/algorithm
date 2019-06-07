package com.test.algorithm.linked;



/**
 *24 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * **/
public class SwapNodesinPairs {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
    //递归法
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
    //直接法
    public static ListNode swapPairs1(ListNode head) {
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
