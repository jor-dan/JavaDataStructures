import java.util.NoSuchElementException;

/**
 * A deque, or double ended queue, is a data structure that allows
 * insertion and removal of elements at both the front and the back.
 * As a result, a deque can be used as a queue or a stack.
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
         * @param prev the previous node in the deque to refer to
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
    /** Maximum size of the deque */
    private int maxSize;

    /** Constructs an empty deque */
    public Deque() {
        this.front = this.back = null;
        this.size = 0;
        this.maxSize = Integer.MAX_VALUE;
    }

    /**
     * Constructs an empty deque
     *
     * @param maxSize the maximum size of the deque
     * @throws IllegalArgumentException if maxSize is not at least 1
     */
    public Deque(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Maximum size must be >= 1");
        }
        this.front = this.back = null;
        this.size = 0;
        this.maxSize = maxSize;
    }

    /**
     * Gets the size of the deque
     *
     * @return the number of elements in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Gets the maximum size of the deque
     *
     * @return the maximum number of elements allowed in the deque
     */
    public int maxSize() {
        return maxSize;
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
     * Returns whether the deque is full or not
     *
     * @return {@code true} if the deque is full
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * Inserts an element at the front of the deque
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     *         {@code false} if the deque is full and the element can't be added
     * @throws NullPointerException if the element is null
     */
    public boolean enqueueFront(T element) {
        if (element == null) throw new NullPointerException();
        if (isFull()) return false;
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
     *         {@code false} if the deque is full and the element can't be added
     * @throws NullPointerException if the element is null
     */
    public boolean enqueueBack(T element) {
        if (element == null) throw new NullPointerException();
        if (isFull()) return false;
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
     * Gets and removes the last element in the deque
     *
     * @return element at the back of the deque
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
     * @return element at the back of the deque
     *         or {@code null} if deque is empty
     */
    public T back() {
        return back != null ? back.element : null;
    }

    /**
     * Gets, but does not remove, the first element in the deque
     *
     * @return element at the back of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T getFirst() {
        if (front == null) throw new NoSuchElementException();
        return front.element;
    }

    /**
     * Gets, but does not remove, the last element in the deque
     *
     * @return element at the back of the deque
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
     * @return element at the back of the deque
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

    // Queue methods

    /**
     * Inserts an element into the queue represented by this deque
     * (at the back of this deque)
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean enqueue(T element) {
        return enqueueBack(element);
    }
    /**
     * Inserts an element into the queue represented by this deque
     * (at the back of this deque)
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean add(T element) {
        return enqueueBack(element);
    }

    /**
     * Inserts an element into the queue represented by this deque
     * (at the back of this deque)
     *
     * @param element the element to insert
     * @return {@code true} if the element was added to the deque
     * @throws NullPointerException if the element is null
     */
    public boolean offer(T element) {
        return enqueueBack(element);
    }

    /**
     * Gets and removes the first element in the queue represented by this deque
     * (the first element in the deque)
     *
     * @return element at the front of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T remove() {
        T element = dequeueFront();
        if (element == null) throw new NoSuchElementException();
        return element;
    }

    /**
     * Gets and removes the first element in the queue represented by this deque
     * (the first element in the deque)
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T poll() {
        return dequeueFront();
    }

    /**
     * Gets, but does not remove, the first element in queue
     * represented by this deque (the first element in the deque)
     *
     * @return element at the front of the deque
     * @throws NoSuchElementException if deque is empty
     */
    public T element() {
        if (front == null) throw new NoSuchElementException();
        return front.element;
    }

    /**
     * Gets, but does not remove, the first element in queue
     * represented by this deque (the first element in the deque)
     *
     * @return element at the front of the deque
     *         or {@code null} if deque is empty
     */
    public T peek() {
        return front != null ? front.element : null;
    }

    // Stack methods

    /**
     * Pushes an element on to the stack represented by this deque
     * (inserts it at the front of the deque)
     *
     * @param element the element to push on to the stack
     * @return {@code true} if the element was added succesfully
     * @throws NullPointerException if the element is null
     */
    public boolean push(T element) {
        return enqueueFront(element);
    }

    /**
     * Removes the element on top of the stack represented by this deque
     * (the first element in the deque)
     *
     * @return the element on top of the stack represented by this deque
     *         (the element at the front of this deque)
     *          or {@code null} if the deque is empty
     */
    public T pop() {
        return dequeueFront();
    }
}
