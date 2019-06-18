public class SinglyLinkedList<T> {
    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public Boolean isEmpty() {
        return head == null;
    }

    public void insert(T data) {
        head = new Node(data, head);
        size++;
    }

    public void append(T data) {
        size++;
        if (head == null) {
            head = new Node(data);
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);
    }

    public Boolean delete(T data) {
        if (head == null) return false;
        if (data == head.data) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (data == current.next.data) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void reverse() {
        Node current = head;
        Node previous = null;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    public T get(int index) {
        if (head != null && index < size()) {
            for (Node current = head; index >= 0; index--) {
                if (index == 0) return current.data;
                current = current.next;
            }
        }
        return null;
    }
}
