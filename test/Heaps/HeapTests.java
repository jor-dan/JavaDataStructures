package Heaps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

@DisplayName("Heap")
public class HeapTests {

    @Nested
    @DisplayName("Max Heap")
    class MaxHeapTest {
        MaxHeap heap;

        @BeforeEach
        void instantiate() {
            heap = new MaxHeap();
        }

        @Test
        void empty() {
            assertTrue(heap.isEmpty());
            assertEquals(0, heap.size());
        }

        @Test
        void insert() {
            int size  = 0;
            for (int i = 1; i < 4; i++) {
                heap.insert(i);
                assertFalse(heap.isEmpty());
                assertEquals(++size, heap.size());
            }
        }

        @Test
        void getMax() {
            assertThrows(NoSuchElementException.class, () -> {
                heap.getMax();
            });
            heap.insert(1);
            assertEquals(1, heap.size());
            assertEquals(1, heap.getMax());
            assertEquals(1, heap.size());
            heap.insert(0);
            assertEquals(2, heap.size());
            assertEquals(1, heap.getMax());
            assertEquals(2, heap.size());
            heap.insert(2);
            assertEquals(3, heap.size());
            assertEquals(2, heap.getMax());
            assertEquals(3, heap.size());
        }

        @Test
        void removeMax() {
            assertThrows(NoSuchElementException.class, () -> {
                heap.removeMax();
            });
            Integer[] nums = {
                4, 2, 1, 0, 3, 7, 6, 5, 9, 8, -5, -6, -9, 12, 11, 10, -10
            };
            for (int num : nums) {
                heap.insert(num);
            }
            Arrays.sort(nums, Collections.reverseOrder());
            for (int num : nums) {
                assertEquals(num, heap.removeMax());
            }
        }
    }

    @Nested
    @DisplayName("Min Heap")
    class MinHeapTest {
        MinHeap heap;

        @BeforeEach
        void instantiate() {
            heap = new MinHeap();
        }

        @Test
        void empty() {
            assertTrue(heap.isEmpty());
            assertEquals(0, heap.size());
        }

        @Test
        void insert() {
            int size = 0;
            for (int i = 3; i > 0; i--) {
                heap.insert(i);
                assertFalse(heap.isEmpty());
                assertEquals(++size, heap.size());
            }
        }

        @Test
        void getMin() {
            assertThrows(NoSuchElementException.class, () -> {
                heap.getMin();
            });
            heap.insert(1);
            assertEquals(1, heap.size());
            assertEquals(1, heap.getMin());
            assertEquals(1, heap.size());
            heap.insert(2);
            assertEquals(2, heap.size());
            assertEquals(1, heap.getMin());
            assertEquals(2, heap.size());
            heap.insert(0);
            assertEquals(3, heap.size());
            assertEquals(0, heap.getMin());
            assertEquals(3, heap.size());
        }

        @Test
        void removeMin() {
            assertThrows(NoSuchElementException.class, () -> {
                heap.removeMin();
            });
            Integer[] nums = {
                4, 2, 1, 0, 3, 7, 6, 5, 9, 8, -5, -6, -9, 12, 11, 10, -10
            };
            for (int num : nums) {
                heap.insert(num);
            }
            Arrays.sort(nums);
            for (int num : nums) {
                assertEquals(num, heap.removeMin());
            }
        }
    }
}
