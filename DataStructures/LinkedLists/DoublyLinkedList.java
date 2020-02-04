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
        T element;
        /** Pointer to the previous node in the list */
        Node<T> prev;
        /** Pointer to the next node in the list */
        Node<T> next;

        /**
         * Constructs a node storing an element 
         * and pointing to the next and previous nodes in the list
         * 
         * @param element the element to be stored in the node
         * @param prev the previous node in the list to point to
         * @param next the next node in the list to point to
         */
        Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
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
     * @throws NullPointerException if array or its elements are null
     */
    public DoublyLinkedList(T[] array) {
        if (array == null) throw new NullPointerException();
        this.head = null;
        this.size = 0;
        for (T element : array) {
            add(element);
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
     * @param element the element being inserted
     * @return {@code true} if the element was added to the list
     * @throws NullPointerException if element is null
     */
    public boolean add(T element) {
        if (element == null) throw new NullPointerException();
        if (head == null) {
            head = new Node<>(element, null, null);
            tail = head;
        } else {
            tail.next = new Node<>(element, tail, null);
            tail = tail.next;
        }
        size++;
        return true;
    }

    /**
     * Adds an element at a specific position in the list
     *
     * @param index the position to add the element at
     * @param element the element to add to the list
     * @throws NullPointerException if element is null
     * @throws IndexOutOfBoundsException if {@code index < 0 || index > size()}
     */
    public void add(int index, T element) {
        if (element == null) throw new NullPointerException();
        if (index == size()) {
            add(element);
        } else {
            Node<T> curr = node(index);
            Node<T> prev = curr.prev;
            Node<T> node = new Node<>(element, prev, curr);
            curr.prev = node;
            if (prev == null) {
                head = node;
            } else {
                prev.next = node;
            }
            size++;
        }
    }

    /**
     * Removes an element from the list
     *
     * @param o the element being removed
     * @return {@code true} if the deletion was successful
     * @throws NullPointerException if element is null
     */
    public boolean remove(Object o) {
        if (o == null) throw new NullPointerException();
        if (head == null) return false;
        if (o.equals(head.element)) {
            remove(head);
            return true;
        }
        if (o.equals(tail.element)) {
            remove(tail);
            return true;
        }
        for (Node<T> curr = head; curr != null; curr = curr.next) {
            if (o.equals(curr.element)) {
                remove(curr);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an element from the list
     *
     * @param index the index of the element being removed
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if {@code index < 0 || index >= size()}
     */
    public T remove(int index) {
        Node<T> node = node(index);
        remove(node);
        return node.element;
    }

    private void remove(Node<T> node) {
        this.size--;
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = head.next;
            if (head == null) tail = null;
            else head.prev = null;
            return;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    /**
     * Replaces an element in the list
     *
     * @param index the index of the element to replace
     * @param element the new element to replace the old element with
     * @return the replaced element
     * @throws NullPointerException if the new element is null
     * @throws IndexOutOfBoundsException if {@code index < 0 || index >= size()}
     */
    public T set(int index, T element) {
        if (element == null) throw new NullPointerException();
        Node<T> node = node(index);
        T prev = node.element;
        node.element = element;
        return prev;
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
     * @throws IndexOutOfBoundsException if {@code index < 0 || index >= size()}
     */
    public T get(int index) {
        return node(index).element;
    }

    private Node<T> node(int index) {
        if (index < size) {
            for (Node<T> curr = head; index >= 0; index--) {
                if (index == 0) return curr;
                curr = curr.next;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Returns if an element is in the list
     *
     * @param o element being searched for in the list
     * @return {@code true} if the element is in the list
     * @throws NullPointerException if data is null
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Finds the index the element first appears at in the list
     *
     * @param o element being searched for in the list
     * @return the index the element appears at in the list
     *         or -1 if the element is not in the list
     * @throws NullPointerException if o is null
     */
    public int indexOf(Object o) {
        if (o == null) throw new NullPointerException();
        Node<T> current = head;
        for (int i = 0; current != null; i++) {
            if (o.equals(current.element)) return i;
            current = current.next;
        }
        return -1;
    }
}
