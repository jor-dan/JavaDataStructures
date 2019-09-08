package Heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Heap where the value of each node is always
 * greater than or equal to the values of its children.
 *
 * @author Jordan Owens
 */
public class MaxHeap {
    private final ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    /**
     * Gets the size of the heap
     *
     * @return the number of elements in the heap
     */
    public int size() {
        return heap.size();
    }

    /**
     * Returns whether the heap is empty or not
     *
     * @return {@code true} if the heap is empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Gets the index of the child's parent
     *
     * @param child the index of the child
     * @return the index of the parent of the child
     */
    private int parent(int child) {
        return (child - 1)/2;
    }

    /**
     * Gets the index of the parent's left child
     *
     * @param parent the index of the parent
     * @return the index of the parent's left child
     */
    private int left(int parent) {
        return parent*2 + 1;
    }

    /**
     * Gets the index of the parent's right child
     *
     * @param parent the index of the parent
     * @return the index of the parent's right child
     */
    private int right(int parent) {
        return parent*2 + 2;
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
     * Swaps two elements in the heap
     *
     * @param a index to move element at index b to
     * @param b index to move element at index a to
     */
    private void swap(int a, int b) {
        heap.set(a, heap.set(b, heap.get(a)));
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
}
