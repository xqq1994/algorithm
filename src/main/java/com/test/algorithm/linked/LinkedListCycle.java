package com.test.algorithm.linked;


/**
 * @Author xiaoqiangqiang
 * @Description //TODO 141 判断链表是否有环
 * 1 hashSet 2 快慢指针
 * @Date 14:42 2019/5/29
 * @Param
 * @return
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 *
 * 示例 2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 *
 *  
 *
 * 进阶：
 *
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
