import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Use a Min-Heap to store the current head of each linked list
        // We compare nodes based on their integer value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 1. Initial Step: Add the head of every non-empty list into the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // Dummy head makes it easier to build the resulting list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 2. Main Loop: Extract the smallest node and add its successor to the heap
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            current.next = smallestNode;
            current = current.next;

            // If the extracted node has a next node, add it to the heap
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }

        return dummy.next;
    }
}