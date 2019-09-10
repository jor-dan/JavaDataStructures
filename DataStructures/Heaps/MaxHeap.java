package Heaps;

import java.util.NoSuchElementException;

/**
 * Heap where the value of each node is always
 * greater than or equal to the values of its children.
 *
 * @author Jordan Owens
 */
public class MaxHeap extends Heap {

    public MaxHeap() {
        super();
    }

    private void heapifyDown() {
        int i = 0, prevLargest;
        do {
            int left = left(i), right = right(i), largest = prevLargest = i;
            if (left < heap.size() && heap.get(left) > heap.get(i)) {
                largest = left;
            }
            if (right < heap.size() && heap.get(right) > heap.get(largest)) {
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
        while (i > 0 && heap.get(i) > heap.get(parent)) {
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
     * Gets the largest number in the heap without removing it
     *
     * @return the largest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public int getMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public int get() {
        return getMax();
    }

    /**
     * Removes the largest number in the heap
     *
     * @return the largest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public int removeMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        if (heap.size() == 1) {
            return heap.remove(0);
        }
        int max = heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown();
        return max;
    }

    public int remove() {
        return removeMax();
    }
}
