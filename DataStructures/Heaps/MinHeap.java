package Heaps;

import java.util.NoSuchElementException;

/**
 * Heap where the value of each node is always
 * less than or equal to the values of its children.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the min heap
 */
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap() {
        super();
    }

    protected void heapifyDown() {
        int i = 0, prevSmallest;
        do {
            int left = left(i), right = right(i), smallest = prevSmallest = i;
            if (left < heap.size() && greater(i, left)) {
                smallest = left;
            }
            if (right < heap.size() && greater(smallest, right)) {
                smallest = right;
            }
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;
            }
        } while (prevSmallest != i);
    }

    private void heapifyUp() {
        int i = heap.size() - 1;
        int parent = parent(i);
        while (i > 0 && greater(parent, i)) {
            swap(parent, i);
            i = parent;
            parent = parent(i);
        }
    }

    /**
     * Inserts an element into the heap
     *
     * @param element the element to insert
     */
    public void insert(T element) {
        heap.add(element);
        heapifyUp();
    }

    /**
     * Gets the smallest element in the heap without removing it
     *
     * @return the largest element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public T getMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public T get() {
        return getMin();
    }

    /**
     * Removes the smallest element in the heap
     *
     * @return the smallest element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public T removeMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        T min = heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown();
        return min;
    }

    public T remove() {
        return removeMin();
    }
}
