package Stacks;

/**
 * Implementation of a Stack
 * <p>
 * Stacks are a last in, first out (LIFO) data structure.
 * Elements are popped off the stack in the opposite order
 * they were pushed on to the stack.
 * The top of the stack is most recent element pushed on to the stack
 * and is the first element to be popped off of the stack.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the stack
 */
public class Stack<T> {
    /** Implementation of the nodes that make up the stack */
    private static class Node<T> {
        /** Element the node stores */
        T element;
        /** Reference to the next node in the stack */
        Node<T> next;

        /**
         * Constructs a node storing an element and referencing another node
         *
         * @param element the element to be stored in the node
         * @param next the next node in the stack to refer to
         */
        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    /** Top of the stack */
    private Node<T> top;
    /** Size of the stack */
    private int size;
    /** Maximum size of the stack */
    private int maxSize;

    /** Constructs an empty stack with no size restriction */
    public Stack() {
        this.top = null;
        this.size = 0;
        this.maxSize = Integer.MAX_VALUE;
    }

    /**
     * Constructs an empty stack with a size restriction
     *
     * @param maxSize the maximum size of the stack
     * @throws IllegalArgumentException if maxSize is not at least 1
     */
    public Stack(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Maximum size must be >= 1");
        }
        this.top = null;
        this.size = 0;
        this.maxSize = maxSize;
    }

    /**
     * Gets the size of the stack
     *
     * @return the number of elements in the stack
     */
    public int size() {
        return size;
    }

    /**
     * Gets the maximum size of the stack
     *
     * @return the maximum number of elements allowed in the stack
     */
    public int maxSize() {
        return maxSize;
    }

    /**
     * Returns whether the stack is empty or not
     *
     * @return {@code true} if the stack is empty
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns whether the stack is full or not
     *
     * @return {@code true} if the stack is full
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * Pushes an element on to the stack
     *
     * @param element the element to push on to the stack
     * @return {@code true} if the element was added successfully
     *         {@code false} if the stack is full and the element can't be added
     * @throws NullPointerException if the element is null
     */
    public boolean push(T element) {
        if (element == null) throw new NullPointerException();
        if (isFull()) return false;
        top = new Node<>(element, top);
        size++;
        return true;
    }

    /**
     * Removes the element on top of the stack
     *
     * @return the element on top of the stack
     *         or {@code null} if the stack is empty
     */
    public T pop() {
        if (top == null) return null;
        T popped = top.element;
        top = top.next;
        size--;
        return popped;
    }

    /**
     * Gets the element on top of the stack
     *
     * @return the element on top of the stack
     *         or {@code null} if the stack is empty
     */
    public T top() {
        return top != null ? top.element : null;
    }
}
