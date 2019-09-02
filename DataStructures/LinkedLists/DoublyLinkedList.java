package LinkedLists;

/**
 * Implementation of a Doubly Linked List
 * <p>
 * Linked lists are similar to arrays, but aren't fixed in size.
 * Linked lists expand and shrink as nodes are dynamically added and removed.
 * In a doubly linked list, each node points to the previous and next node.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the linked list
 */
public class DoublyLinkedList<T> {
    /** Implementation of the nodes that make up the linked list */
    private static class Node<T> {
        /** Element the node stores */
        T data;
        /** Pointer to the previous node in the list */
        Node<T> prev;
        /** Pointer to the next node in the list */
        Node<T> next;

        /**
         * Constructs a node storing an element 
         * and pointing to the next and previous nodes in the list
         * 
         * @param data the element to be stored in the node
         * @param prev the previous node in the list to point to
         * @param next the next node in the list to point to
         */
        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /** Head/front of the list */
    private Node<T> head;
    /** Tail/back of the list */
    private Node<T> tail;
    /** Size of the list */
    private int size;

    /** Constructs an empty doubly linked list */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Constructs a doubly linked list from array elements
     *
     * @param array array to copy elements from
     * @throws IllegalArgumentException if array or its elements are null
     */
    public DoublyLinkedList(T[] array) {
        if (array == null) throw new IllegalArgumentException();
        this.head = null;
        this.size = 0;
        for (T element : array) {
            insert(element);
        }
    }

    /**
     * Gets the size of the linked list
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the list is empty or not
     *
     * @return {@code true} if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts element at the end of the list
     *
     * @param data the data being inserted
     * @throws IllegalArgumentException if data is null
     */
    public void insert(T data) {
        if (data == null) throw new IllegalArgumentException();
        if (head == null) {
            head = new Node<>(data, null, null);
            tail = head;
        } else {
            tail.next = new Node<>(data, tail, null);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Removes an element from the list
     *
     * @param data the data being removed
     * @return {@code true} if the deletion was successful
     * @throws IllegalArgumentException if data is null
     */
    public boolean delete(T data) {
        if (data == null) throw new IllegalArgumentException();
        if (head == null) return false;
        if (data.equals(head.data)) {
            head = head.next;
            if (head == null) tail = null;
            else head.prev = null;
            size--;
            return true;
        }
        if (data.equals(tail.data)) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return true;
        }
        for (Node<T> curr = head; curr != null; curr = curr.next) {
            if (data.equals(curr.data)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Reverses the order of the list
     */
    public void reverse() {
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null) {
            Node<T> next = current.next;
            current.next = previous;
            current.prev = next;
            previous = current;
            current = next;
        }
        tail = head;
        head = previous;
    }

    /**
     * Gets the element at a specific position in the list
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size()
     */
    public T get(int index) {
        if (head != null && index < size) {
            if (index == size-1) return tail.data;
            for (Node<T> current = head; index >= 0; index--) {
                if (index == 0) return current.data;
                current = current.next;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Returns if an element is in the list
     *
     * @param data element being searched for in the list
     * @return {@code true} if the element is in the list
     * @throws IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    /**
     * Finds the index the element first appears at in the list
     *
     * @param data element being searched for in the list
     * @return the index the element appears at in the list
     *         or -1 if the element is not in the list
     * @throws IllegalArgumentException if data is null
     */
    public int indexOf(T data) {
        if (data == null) throw new IllegalArgumentException();
        Node<T> current = head;
        for (int i = 0; current != null; i++) {
            if (data.equals(current.data)) return i;
            current = current.next;
        }
        return -1;
    }
}
