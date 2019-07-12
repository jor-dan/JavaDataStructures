import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

@DisplayName("Deque")
public class DequeTest {
    Deque<Integer> deque;

    @BeforeEach
    void instantiate() {
        deque = new Deque<>();
    }

    @Test
    void empty() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    void enqueue() {
        assertThrows(NullPointerException.class, () -> {
            deque.enqueueFront(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.enqueueBack(null);
        });
        assertEquals(0, deque.size());
        deque.enqueueFront(1);
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
        deque.enqueueFront(2);
        deque.enqueueBack(3);
        assertEquals(3, deque.size());
    }

    @Test
    void add() {
        assertThrows(NullPointerException.class, () -> {
            deque.addFirst(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.addLast(null);
        });
        deque.addFirst(5);
        assertEquals(1, deque.size());
        deque.addLast(5);
        assertEquals(2, deque.size());
    }

    @Test
    void offer() {
        assertThrows(NullPointerException.class, () -> {
            deque.offerFirst(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.offerLast(null);
        });
        assertTrue(deque.offerFirst(5));
        assertEquals(1, deque.size());
        assertTrue(deque.offerLast(5));
        assertEquals(2, deque.size());
    }

    @Test
    void dequeue() {
        assertNull(deque.dequeueFront());
        assertNull(deque.dequeueBack());
        deque.enqueueBack(1);
        deque.enqueueBack(2);
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());
        assertEquals(1, deque.dequeueFront());
        assertEquals(2, deque.dequeueFront());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    @Test
    void remove() {
        assertThrows(NoSuchElementException.class, () -> {
            deque.removeFirst();
        });
        assertThrows(NoSuchElementException.class, () -> {
            deque.removeLast();
        });
        deque.enqueueFront(5);
        assertEquals(1, deque.size());
        assertEquals(5, deque.removeFirst());
        deque.enqueueFront(10);
        assertEquals(10, deque.removeLast());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    @Test
    void poll() {
        assertNull(deque.pollFirst());
        assertNull(deque.pollLast());
        deque.enqueueFront(5);
        assertEquals(1, deque.size());
        assertEquals(5, deque.pollFirst());
        deque.enqueueFront(5);
        assertEquals(5, deque.pollLast());
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());
    }

    @Test
    @DisplayName("gets front element without removal")
    void front() {
        assertNull(deque.front());
        assertNull(deque.peekFirst());
        assertThrows(NoSuchElementException.class, () -> {
            deque.getFirst();
        });
        deque.enqueueFront(1);
        assertEquals(1, deque.size());
        assertEquals(1, deque.front());
        assertEquals(1, deque.size());
        assertEquals(1, deque.peekFirst());
        assertEquals(1, deque.size());
        assertEquals(1, deque.getFirst());
        assertEquals(1, deque.size());
    }

    @Test
    @DisplayName("gets back element without removal")
    void back() {
        assertNull(deque.back());
        assertNull(deque.peekLast());
        assertThrows(NoSuchElementException.class, () -> {
            deque.getLast();
        });
        deque.enqueueBack(1);
        assertEquals(1, deque.size());
        assertEquals(1, deque.back());
        assertEquals(1, deque.size());
        assertEquals(1, deque.peekLast());
        assertEquals(1, deque.size());
        assertEquals(1, deque.getLast());
        assertEquals(1, deque.size());
    }

    @Test
    void contains() {
        assertThrows(NullPointerException.class, () -> {
            deque.contains(null);
        });
        assertFalse(deque.contains(10));
        deque.enqueueFront(1);
        deque.enqueueBack(2);
        assertTrue(deque.contains(1));
        assertTrue(deque.contains(2));
        assertFalse(deque.contains(10));
    }
}
