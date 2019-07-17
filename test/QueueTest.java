import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

@DisplayName("Queue")
public class QueueTest {
    Queue<Integer> queue;

    @BeforeEach
    void instantiate() {
        queue = new Queue<>();
    }

    @Test
    @DisplayName("is proper size after instantiation")
    void postInstantiation() {
        // Starts off empty
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        // Is not full
        assertFalse(queue.isFull());
        // Is not size restricted
        assertEquals(Integer.MAX_VALUE, queue.maxSize());
    }

    @Test
    void enqueue() {
        assertThrows(NullPointerException.class, () -> {
            queue.enqueue(null);
        });
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        queue.enqueue(2);
        assertEquals(2, queue.size());
    }

    @Test
    void add() {
        assertThrows(NullPointerException.class, () -> {
            queue.add(null);
        });
        assertTrue(queue.add(5));
        assertEquals(1, queue.size());
    }

    @Test
    void offer() {
        assertThrows(NullPointerException.class, () -> {
            queue.offer(null);
        });
        assertTrue(queue.offer(5));
        assertEquals(1, queue.size());
    }

    @Test
    void dequeue() {
        assertNull(queue.dequeue());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void remove() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.remove();
        });
        queue.enqueue(5);
        assertEquals(1, queue.size());
        assertEquals(5, queue.remove());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void poll() {
        assertNull(queue.poll());
        queue.enqueue(5);
        assertEquals(1, queue.size());
        assertEquals(5, queue.poll());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void element() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.element();
        });
        queue.enqueue(5);
        assertEquals(1, queue.size());
        assertEquals(5, queue.element());
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("front/peek")
    void peek() {
        assertNull(queue.front());
        assertNull(queue.peek());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        assertEquals(1, queue.front());
        assertEquals(1, queue.size());
        assertEquals(1, queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("handles instantiation with a size limit")
    void instantiateWithSizeLimits() {
        assertThrows(IllegalArgumentException.class, () -> {
           queue = new Queue<>(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            queue = new Queue<>(0);
        });
        assertDoesNotThrow(() -> {
            queue = new Queue<>(1);
        });
    }

    @Test
    @DisplayName("handles size constraints")
    void sizeConstraintOperations() {
        queue = new Queue<>(1);
        assertEquals(1, queue.maxSize());
        assertFalse(queue.isFull());
        assertTrue(queue.enqueue(1));
        assertTrue(queue.isFull());
        assertFalse(queue.enqueue(2));
        // Throws NullPointerException even if queue is full
        assertThrows(NullPointerException.class, () -> {
            queue.enqueue(null);
        });
    }
}
