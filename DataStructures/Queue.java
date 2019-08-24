import java.util.NoSuchElementException;

/**
 * Implementation of a Queue
 * <p>
 * Queues are a first-in, first-out (FIFO) data structure.
 * Elements are removed in the same order they were inserted.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the queue
 */
public class Queue<T> {
    /** Implementation of the nodes that make up the queue */
    private static class Node<T> {
        /** Element the node stores */
        T element;
        /** Reference to the next node in the queue */
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
    }

    /** First node in the queue */
    private Node<T> front;
    /** Last node in the queue */
    private Node<T> back;
    /** Size of the queue */
    private int size;
    /** Maximum size of the queue */
    private int maxSize;

    /** Constructs an empty queue */
    public Queue() {
        this.front = this.back = null;
        this.size = 0;
        this.maxSize = Integer.MAX_VALUE;
    }

    /**
     * Constructs an empty queue
     *
     * @param maxSize the maximum size of the queue
     * @throws IllegalArgumentException if maxSize is not at least 1
     */
    public Queue(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Maximum size must be >= 1");
        }
        this.front = this.back = null;
        this.size = 0;
        this.maxSize = maxSize;
    }

    /**
     * Gets the size of the queue
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Gets the maximum size of the queue
     *
     * @return the maximum number of elements allowed in the queue
     */
    public int maxSize() {
        return maxSize;
    }

    /**
     * Returns whether the queue is empty or not
     *
     * @return {@code true} if the queue is empty
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Returns whether the queue is full or not
     *
     * @return {@code true} if the queue is full
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @return {@code true} if the element was added successfully
     *         {@code false} if the queue is full and the element can't be added
     * @throws NullPointerException if the element is null
     */
    public boolean enqueue(T element) {
        if (element == null) throw new NullPointerException();
        if (isFull()) return false;
        if (front == null) {
            front = new Node<>(element);
            back = front;
        } else {
            back.next = new Node<>(element);
            back = back.next;
        }
        size++;
        return true;
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @return {@code true} if the element was added to the queue
     * @throws NullPointerException if the element is null
     */
    public boolean add(T element) {
        return enqueue(element);
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @return {@code true} if the element was added to the queue
     * @throws NullPointerException if the element is null
     */
    public boolean offer(T element) {
        return enqueue(element);
    }

    /**
     * Gets and removes the front of the queue
     *
     * @return element at the front of the queue
     *         or {@code null} if queue is empty
     */
    public T dequeue() {
        if (front == null) return null;
        T dequeued = front.element;
        front = front.next;
        size--;
        return dequeued;
    }

    /**
     * Gets and removes the front of the queue
     *
     * @return element at the front of the queue
     * @throws NoSuchElementException if queue is empty
     */
    public T remove() {
        T element = dequeue();
        if (element == null) throw new NoSuchElementException();
        return element;
    }

    /**
     * Gets and removes the front of the queue
     *
     * @return element at the front of the queue
     *         or {@code null} if queue is empty
     */
    public T poll() {
        return dequeue();
    }

    /**
     * Gets element at the front of the queue
     *
     * @return element at the front of the queue
     *         or {@code null} if queue is empty
     */
    public T front() {
        return front != null ? front.element : null;
    }

    /**
     * Gets element at the front of the queue
     *
     * @return element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T element() {
        if (front == null) throw new NoSuchElementException();
        return front.element;
    }

    /**
     * Gets element at the front of the queue
     *
     * @return element at the front of the queue
     *         or {@code null} if queue is empty
     */
    public T peek() {
        return front != null ? front.element : null;
    }
}
