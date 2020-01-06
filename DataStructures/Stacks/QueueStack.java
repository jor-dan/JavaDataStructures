package Stacks;

import Queues.Queue;

/**
 * Stack implemented using a Queue
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the stack
 */
public class QueueStack<T> {
    private Queue<T> queue;

    /** Constructs an empty stack */
    public QueueStack() {
        queue = new Queue<>();
    }

    /**
     * Gets the size of the stack
     *
     * @return the number of elements in the stack
     */
    public int size() {
        return queue.size();
    }

    /**
     * Returns whether the stack is empty or not
     *
     * @return {@code true} if the stack is empty
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Pushes an element on to the stack
     *
     * @param element the element to push on to the stack
     * @throws NullPointerException if the element is null
     */
    public void push(T element) {
        if (element == null) throw new NullPointerException();
        queue.enqueue(element);
        for (int size = queue.size(); size > 1; size--) {
            queue.enqueue(queue.dequeue());
        }
    }

    /**
     * Removes the element on top of the stack
     *
     * @return the element on top of the stack
     *         or {@code null} if the stack is empty
     */
    public T pop() {
        return queue.dequeue();
    }

    /**
     * Gets the element on top of the stack
     *
     * @return the element on top of the stack
     *         or {@code null} if the stack is empty
     */
    public T top() {
        return queue.peek();
    }
}
