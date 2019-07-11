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

    /** Constructs an empty queue */
    public Queue() {
        this.front = null;
        this.back = null;
        this.size = 0;
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
     * Returns whether the queue is empty or not
     * 
     * @return {@code true} if the queue is empty
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @throws NullPointerException if the element is null
     */
    public void enqueue(T element) {
        if (element == null) throw new NullPointerException();
        if (front == null) {
            front = new Node<>(element);
            back = front;
        } else {
            back.next = new Node<>(element);
            back = back.next;
        }
        size++;
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @return {@code true} if the element was added to the queue
     * @throws NullPointerException if the element is null
     */
    public boolean add(T element) {
        enqueue(element);
        return true;
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @return {@code true} if the element was added to the queue
     * @throws NullPointerException if the element is null
     */
    public boolean offer(T element) {
        enqueue(element);
        return true;
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
