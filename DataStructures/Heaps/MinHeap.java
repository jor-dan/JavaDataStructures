package Heaps;

import java.util.NoSuchElementException;

/**
 * Heap where the value of each node is always
 * less than or equal to the values of its children.
 *
 * @author Jordan Owens
 */
public class MinHeap extends Heap {

    public MinHeap() {
        super();
    }

    private void heapifyDown() {
        int i = 0, prevSmallest;
        do {
            int left = left(i), right = right(i), smallest = prevSmallest = i;
            if (left < heap.size() && heap.get(i) > heap.get(left)) {
                smallest = left;
            }
            if (right < heap.size() && heap.get(smallest) > heap.get(right)) {
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
        while (i > 0 && heap.get(parent) > heap.get(i)) {
            swap(parent, i);
            i = parent;
            parent = parent(i);
        }
    }

    /**
     * Inserts a number into the heap
     *
     * @param value the number to insert
     */
    public void insert(int value) {
        heap.add(value);
        heapifyUp();
    }

    /**
     * Gets the smallest number in the heap without removing it
     *
     * @return the largest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public int getMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public int get() {
        return getMin();
    }

    /**
     * Removes the smallest number in the heap
     *
     * @return the smallest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public int removeMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        int min = heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown();
        return min;
    }

    public int remove() {
        return removeMin();
    }
}
