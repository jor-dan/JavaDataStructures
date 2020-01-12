package Heaps;

import java.util.NoSuchElementException;

/**
 * Heap where the value of each node is always
 * greater than or equal to the values of its children.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the max heap
 */
public class MaxHeap<T extends Comparable<T>> extends Heap<T> {

    public MaxHeap() {
        super();
    }

    private void heapifyDown() {
        int i = 0, prevLargest;
        do {
            int left = left(i), right = right(i), largest = prevLargest = i;
            if (left < heap.size() && greater(left, i)) {
                largest = left;
            }
            if (right < heap.size() && greater(right, largest)) {
                largest = right;
            }
            if (largest != i) {
                swap(i, largest);
                i = largest;
            }
        } while (prevLargest != i);
    }

    private void heapifyUp() {
        int i = heap.size() - 1;
        int parent = parent(i);
        while (i > 0 && greater(i, parent)) {
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
     * Gets the largest element in the heap without removing it
     *
     * @return the largest element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public T get() {
        return getMax();
    }

    /**
     * Removes the largest element in the heap
     *
     * @return the largest element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public T removeMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        T max = heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown();
        return max;
    }

    public T remove() {
        return removeMax();
    }
}
