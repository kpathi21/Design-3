import java.util.HashMap;

public class LRUCache {
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    class Node {
        int key;
        int value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node); // MRU - most recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            map.put(key, node);

            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                Node lastNode = tail.prev;

                removeNode(lastNode);
                map.remove(lastNode.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void removeNode(Node node) {
        Node start = node.prev;
        start.next = node.next;
        node.next.prev = start;
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node) { //MRU
        Node end = head.next;
        node.next = end;
        node.prev = head;
        end.prev = node;
        head.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

//All methods happen in O(1) time complexity
//SC: O(capacity)