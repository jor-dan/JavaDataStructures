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
    void empty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
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
    void peek() {
        assertNull(queue.peek());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        assertEquals(1, queue.peek());
        assertEquals(1, queue.size());
    }
}
