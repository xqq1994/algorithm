package com.test.algorithm.linked;

/**
 * @Author xiaoqiangqiang
 * @Description //TODO 142 环形链表 II
 * 1 hashSet 2 快慢指针
 * @Date 14:42 2019/5/29
 * @Param
 * @return
 */
public class LinkedListCycleII {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    /**
     * @Author xiaoqiangqiang
     * @Description //TODO 快慢指针,复用快指针
     * @Date 19:32 2019/5/29
     * @Param 
     * @return 
     */
    public ListNode hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean flag = false;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                flag = true;
                break;
            }
        }
        if(flag){
            fast = head;
            while (fast != slow){
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
        return null;
    }
}
