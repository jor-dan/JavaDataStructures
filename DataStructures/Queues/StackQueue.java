package Queues;

import Stacks.Stack;

/**
 * Queue implemented using Stacks
 *
 * @author Jordan Owens
 */
public class StackQueue<T> {
    private Stack<T> s1;
    private Stack<T> s2;
    private T front;

    public StackQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
        this.front = null;
    }

    /**
     * Gets the size of the queue
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return s1.size() + s2.size();
    }

    /**
     * Returns whether the queue is empty or not
     *
     * @return {@code true} if the queue is empty
     */
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    /**
     * Inserts an element into the queue
     *
     * @param element the element to add
     * @throws NullPointerException if the element is null
     */
    public void enqueue(T element) {
        if (element == null) throw new NullPointerException();
        if (s1.isEmpty()) front = element;
        s1.push(element);
    }

    /**
     * Gets and removes the front of the queue
     *
     * @return element at the front of the queue
     *         or {@code null} if queue is empty
     */
    public T dequeue() {
        if (s2.isEmpty()) {
            while (s1.size() > 1) {
                s2.push(s1.pop());
            }
            front = null;
            return s1.pop();
        }
        return s2.pop();
    }

    /**
     * Gets element at the front of the queue
     *
     * @return element at the front of the queue
     *         or {@code null} if queue is empty
     */
    public T peek() {
        return s2.isEmpty() ? front : s2.top();
    }
}
