import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

@DisplayName("MaxHeap")
public class MaxHeapTest {
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
        int[] nums = { 3, 4, 5 };
        int size  = 0;
        for (int num : nums) {
            heap.insert(num);
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
        Integer[] nums = { 4, 2, 1, 0, 3, 7, 6, 5, 9, 8, -5, -6, -9, 12, 11, 10, -10 };
        for (int num : nums) {
            heap.insert(num);
        }
        Arrays.sort(nums, Collections.reverseOrder());
        for (int num : nums) {
            assertEquals(num, heap.removeMax());
        }
    }
}
