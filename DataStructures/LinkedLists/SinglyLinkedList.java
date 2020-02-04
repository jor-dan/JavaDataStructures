package LinkedLists;

/**
 * Implementation of a Singly Linked List
 * <p>
 * Linked lists are similar to arrays, but aren't fixed in size.
 * Linked lists expand and shrink as nodes are dynamically added and removed.
 * In a singly linked list, each node points to the next node in the list.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the linked list
 */
public class SinglyLinkedList<T> {
    /** Implementation of the nodes that make up the linked list */
    private static class Node<T> {
        /** Element the node stores */
        T element;
        /** Pointer to the next node in the list */
        Node<T> next;

        /**
         * Constructs a node storing an element
         *
         * @param element the element to be stored in the node
         */
        Node(T element) {
            this.element = element;
            this.next = null;
        }

        /**
         * Constructs a node storing an element and pointing to another node
         *
         * @param element the element to be stored in the node
         * @param next the next node in the list to point to
         */
        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    /** Front of the list */
    private Node<T> head;
    /** Back of the list */
    private Node<T> tail;
    /** Size of the list */
    private int size;

    /** Constructs an empty linked list */
    public SinglyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Constructs a singly linked list from array elements
     *
     * @param array array to copy elements from
     * @throws IllegalArgumentException if array or its elements are null
     */
    public SinglyLinkedList(T[] array) {
        if (array == null) throw new NullPointerException();
        this.head = this.tail = null;
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
     * Inserts an element at the front of the list
     *
     * @param element the element being inserted
     * @throws NullPointerException if data is null
     */
    public void insert(T element) {
        add(0, element);
    }

    /**
     * Inserts an element at the back of the list
     *
     * @param element the element being inserted
     * @throws NullPointerException if element is null
     */
    public void add(T element) {
        add(size(), element);
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
        if (index == 0) {
            head = new Node<>(element, head);
            if (tail == null) tail = head;
        } else if (index == size()) {
            tail.next = new Node<>(element);
            tail = tail.next;
        } else {
            Node<T> before = node(index - 1);
            before.next = new Node<>(element, before.next);
        }
        size++;
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
            if (head == tail) tail = tail.next;
            head = head.next;
            size--;
            return true;
        }
        for (Node<T> curr = head; curr.next != null; curr = curr.next) {
            if (o.equals(curr.next.element)) {
                if (curr.next == tail) tail = curr;
                curr.next = curr.next.next;
                size--;
                return true;
            }
        }
        return false;
    }

    public T remove(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        T prev;
        if (index == 0) {
            prev = head.element;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
        } else {
            Node<T> before = node(index - 1);
            prev = before.next.element;
            if (before.next == tail) {
                tail = before;
                before.next = null;
            } else {
                before.next = before.next.next;
            }
        }
        size--;
        return prev;
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
            previous = current;
            current = next;
        }
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
     * @throws NullPointerException if element is null
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
     * @throws NullPointerException if data is null
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
