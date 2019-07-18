import java.util.NoSuchElementException;

/**
 * A deque, or double ended queue, is a data structure that allows
 * insertion and removal of elements at both the front and the back.
 * 
 * @author Jordan Owens
 * @param <T> the type of elements in the deque
 */
public class Deque<T> {
    /** Implementation of the nodes that make up the deque */
    private static class Node<T> {
        /** Element the node stores */
        T element;
        /** Reference to the previous node in the deque */
        Node<T> prev;
        /** Reference to the next node in the deque */
        Node<T> next;
        
        /**
         * Constructs a node storing an element
         *
         * @param element the element to be stored in the node
         * @param next the previous node in the deque to refer to
         * @param next the next node in the deque to refer to
         */
        Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    /** First node in the deque */
    private Node<T> front;
    /** Last node in the deque */
    private Node<T> back;
    /** Size of the deque */
    private int size;

    /** Constructs an empty deque */
    public Deque() {
        this.front = this.back = null;
        this.size = 0;
    }

    /**
     * Gets the size of the deque
     *
     * @return the number of elements in the qudequeeue
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the deque is empty or not
     *
     * @return {@code true} if the deque is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts an element at the front of the deque
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean enqueueFront(T element) {
        if (element == null) throw new NullPointerException();
        front = new Node<>(element, null, front);
        if (front.next == null) {
            back = front;
        } else {
            front.next.prev = front;
        }
        size++;
        return true;
    }

    /**
     * Inserts an element at the back of the deque
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean enqueueBack(T element) {
        if (element == null) throw new NullPointerException();
        back = new Node<>(element, back, null);
        if (back.prev == null) {
            front = back;
        } else {
            back.prev.next = back;
        }
        size++;
        return true;
    }

    /**
     * Inserts an element at the front of the deque
     *
     * @param element the element to insert
     * @throws NullPointerException if the element is null
     */
    public void addFirst(T element) {
        enqueueFront(element);
    }

    /**
     * Inserts an element at the back of the deque
     *
     * @param element the element to insert
     * @throws NullPointerException if the element is null
     */
    public void addLast(T element) {
        enqueueBack(element);
    }

    /**
     * Inserts an element at the front of the deque
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean offerFirst(T element) {
        return enqueueFront(element);
    }

    /**
     * Inserts an element at the back of the deque
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean offerLast(T element) {
        return enqueueBack(element);
    }

    /**
     * Gets and removes the first element in the deque
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T dequeueFront() {
        if (front == null) return null;
        T dequeued = front.element;
        front = front.next;
        size--;
        return dequeued;
    }

    /**
     * Gets and removes the last element in the deque
     *
     * @return element at the back of the deque
     *         or {@code null} if deque is empty
     */
    public T dequeueBack() {
        if (back == null) return null;
        T dequeued = back.element;
        back = back.prev;
        size--;
        return dequeued;
    }

    /**
     * Gets and removes the first element in the deque
     *
     * @return element at the front of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T removeFirst() {
        T element = dequeueFront();
        if (element == null) throw new NoSuchElementException();
        return element;
    }

    /**
     * Gets and removes the first element in the deque
     *
     * @return element at the front of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T removeLast() {
        T element = dequeueBack();
        if (element == null) throw new NoSuchElementException();
        return element;
    }

    /**
     * Gets and removes the first element in the deque
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T pollFirst() {
        return dequeueFront();
    }

    /**
     * Gets and removes the last element in the deque
     *
     * @return element at the back of the deque
     *         or {@code null} if deque is empty
     */
    public T pollLast() {
        return dequeueBack();
    }

    /**
     * Gets, but does not remove, the first element in the deque
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T front() {
        return front != null ? front.element : null;
    }

    /**
     * Gets, but does not remove, the last element in the deque
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T back() {
        return back != null ? back.element : null;
    }

    /**
     * Gets, but does not remove, the first element in the deque
     *
     * @return element at the front of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T getFirst() {
        if (front == null) throw new NoSuchElementException();
        return front.element;
    }

    /**
     * Gets, but does not remove, the last element in the deque
     *
     * @return element at the front of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T getLast() {
        if (back == null) throw new NoSuchElementException();
        return back.element;
    }

    /**
     * Gets, but does not remove, the first element in the deque
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T peekFirst() {
        return front != null ? front.element : null;
    }

    /**
     * Gets, but does not remove, the last element in the deque
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T peekLast() {
        return back != null ? back.element : null;
    }

    /**
     * Returns whether the deque contains an element
     *
     * @param o element being tested for inclusion in the deque
     * @return {@code true} if the element is in the deque
     * @throws NullPointerException if the element is null
     */
    public boolean contains(Object o) {
        if (o == null) throw new NullPointerException();
        if (back != null && o.equals(back.element)) return true;
        for (Node<T> current = front; current != null; current = current.next) {
            if (o.equals(current.element)) return true;
        }
        return false;
    }
}
