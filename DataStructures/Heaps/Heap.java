package Heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Binary Heap base class for array implementations.
 *
 * @author Jordan Owens
* @param <T> the type of elements in the heap
 */
public abstract class Heap<T extends Comparable<T>> {

    protected final ArrayList<T> heap;

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
     * Inserts an element into the heap
     *
     * @param element the element to insert
     */
    public abstract void insert(T element);

    /**
     * Gets the largest number in the heap (Max Heap)
     * or the smallest number in the heap (Min Heap)
     * without removing it
     *
     * @return the largest or smallest number in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public abstract T get();

    /**
     * Removes the largest element in the heap (Max Heap)
     * or the smallest element in the heap (Min Heap)
     *
     * @return the largest or smallest element in the heap
     * @throws NoSuchElementException if the heap is empty
     */
    public abstract T remove();

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

    /**
     * Returns whether the element at index a is greater than element at index b
     *
     * @param a index of element to compare with element at index b
     * @param b index of element to compare with element at index a
     * @return {@code true} if element at index a is greater than element at b
     */
    protected boolean greater(int a, int b) {
        return heap.get(a).compareTo(heap.get(b)) > 0;
    }
}
