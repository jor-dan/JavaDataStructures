package Heaps;

import java.util.ArrayList;

/**
 * Binary Heap base class for array implementations.
 *
 * @author Jordan Owens
 */
public abstract class Heap {

    protected final ArrayList<Integer> heap;

    public Heap() {
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
     * Inserts a number into the heap
     *
     * @param value the number to insert
     */
    public abstract void insert(int value);

    /**
     * Gets the largest number in the heap (Max Heap)
     * or the smallest number in the heap (Min Heap)
     * without removing it
     *
     * @return the largest or smallest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public abstract int get();

    /**
     * Removes the largest number in the heap (Max Heap)
     * or the smallest number in the heap (Min Heap)
     *
     * @return the largest or smallest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public abstract int remove();

    /**
     * Gets the index of the child's parent
     *
     * @param child the index of the child
     * @return the index of the parent of the child
     */
    protected static int parent(int child) {
        return (child - 1)/2;
    }

    /**
     * Gets the index of the parent's left child
     *
     * @param parent the index of the parent
     * @return the index of the parent's left child
     */
    protected static int left(int parent) {
        return parent*2 + 1;
    }

    /**
     * Gets the index of the parent's right child
     *
     * @param parent the index of the parent
     * @return the index of the parent's right child
     */
    protected static int right(int parent) {
        return parent*2 + 2;
    }

    /**
     * Swaps two elements in the heap
     *
     * @param a index to move element at index b to
     * @param b index to move element at index a to
     */
    protected void swap(int a, int b) {
        heap.set(a, heap.set(b, heap.get(a)));
    }
}
