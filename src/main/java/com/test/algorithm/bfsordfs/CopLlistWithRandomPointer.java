package com.test.algorithm.bfsordfs;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO 138. 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的深拷贝。 
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 * <p>
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *  
 * <p>
 * 提示：
 * <p>
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 **/
public class CopLlistWithRandomPointer {

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    /**
     * 方法 1：回溯
     * 想法
     * <p>
     * 回溯算法的第一想法是将链表想象成一张图。链表中每个节点都有 2 个指针（图中的边）。因为随机指针给图结构添加了随机性，所以我们可能会访问相同的节点多次，这样就形成了环。
     * <p>
     * <p>
     * <p>
     * 上图中，我们可以看到随机指针指向了前一个节点，因此成环。我们需要考虑这种环的实现。
     * <p>
     * 此方法中，我们只需要遍历整个图并拷贝它。拷贝的意思是每当遇到一个新的未访问过的节点，你都需要创造一个新的节点。遍历按照深度优先进行。我们需要在回溯的过程中记录已经访问过的节点，否则因为随机指针的存在我们可能会产生死循环。
     * <p>
     * 算法
     * <p>
     * 从 头 指针开始遍历整个图。
     * <p>
     * 我们将链表看做一张图。下图对应的是上面的有向链表的例子，Head 是图的出发节点。
     * <p>
     * <p>
     * <p>
     * 当我们遍历到某个点时，如果我们已经有了当前节点的一个拷贝，我们不需要重复进行拷贝。
     * 如果我们还没拷贝过当前节点，我们创造一个新的节点，并把该节点放到已访问字典中，即：
     * visited_dictionary[current_node] = cloned_node_for_current_node.
     * 我们针对两种情况进行回溯调用：一个顺着 random 指针调用，另一个沿着 next 指针调用。步骤 1 中将 random 和 next 指针分别红红色和蓝色标注。然后我们分别对两个指针进行函数递归调用：
     * cloned_node_for_current_node.next = copyRandomList(current_node.next);
     * cloned_node_for_current_node.random = copyRandomList(current_node.random);
     **/
    class Solution {
        private Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            if (map.containsKey(head)) {
                return map.get(head);
            }
            Node node = new Node(head.val, null, null);
            map.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
            return node;
        }
    }

    class Solution1 {
        private Map<Node, Node> map = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node oldNode = head;

            // Creating the new head node.
            Node newNode = new Node(oldNode.val, null, null);
            this.map.put(oldNode, newNode);

            // Iterate on the linked list until all nodes are cloned.
            while (oldNode != null) {
                // Get the clones of the nodes referenced by random and next pointers.
                newNode.random = this.getClonedNode(oldNode.random);
                newNode.next = this.getClonedNode(oldNode.next);

                // Move one step ahead in the linked list.
                oldNode = oldNode.next;
                newNode = newNode.next;
            }
            return this.map.get(head);
        }

        public Node getClonedNode(Node node) {
            // If the node exists then
            if (node != null) {
                // Check if the node is in the visited dictionary
                if (this.map.containsKey(node)) {
                    // If its in the visited dictionary then return the new node reference from the dictionary
                    return this.map.get(node);
                } else {
                    // Otherwise create a new node, add to the dictionary and return it
                    this.map.put(node, new Node(node.val, null, null));
                    return this.map.get(node);
                }
            }
            return null;
        }
    }

    class Solution2 {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            Node ptr = head;
            while (ptr != null) {
                Node newNode = new Node(ptr.val, null, null);
                newNode.next = ptr.next;
                ptr.next = newNode;
                ptr = newNode.next;
            }
            ptr = head;
            while (ptr != null) {
                ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
                ptr = ptr.next.next;
            }
            Node ptr_old_list = head; // A->B->C
            Node ptr_new_list = head.next; // A'->B'->C'
            Node head_old = head.next;
            while (ptr_old_list != null) {
                ptr_old_list.next = ptr_old_list.next.next;
                ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
                ptr_old_list = ptr_old_list.next;
                ptr_new_list = ptr_new_list.next;
            }
            return head_old;
        }
    }

}
