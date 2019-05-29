package com.test.algorithm.linked;

import java.util.HashSet;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 141 判断链表是否有环
 * 1 hashSet 2 快慢指针
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
    /**
     * @Author xiaoqiangqiang
     * @Description //TODO 快慢指针
     * @Date 19:32 2019/5/29
     * @Param 
     * @return 
     */
    public boolean hasCycle(ListNode head) {
        ListNode first = head;
        ListNode secu = head;
        while (secu != null && secu.next != null){
            first = first.next;
            secu = secu.next.next;
            if(first == secu){
                return true;
            }
        }
        return false;
    }
    /**
     * @Author xiaoqiangqiang
     * @Description //TODO haseset
     * @Date 19:32 2019/5/29
     * @Param 
     * @return 
     */
    /*public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if(set.contains(head)){
                return true;
            }else{
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }*/
}
