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
        assertThrows(NullPointerException.class, () -> {
            deque.enqueue(null);
        });
        assertEquals(0, deque.size());
        assertTrue(deque.enqueueFront(1));
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
        assertTrue(deque.enqueueBack(2));
        assertTrue(deque.enqueue(3));
        assertTrue(deque.enqueueFront(4));
        assertEquals(4, deque.size());
    }

    @Test
    void add() {
        assertThrows(NullPointerException.class, () -> {
            deque.addFirst(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.addLast(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.add(null);
        });
        deque.addFirst(5);
        assertEquals(1, deque.size());
        deque.addLast(5);
        assertEquals(2, deque.size());
        assertTrue(deque.add(5));
        assertEquals(3, deque.size());
    }

    @Test
    void offer() {
        assertThrows(NullPointerException.class, () -> {
            deque.offerFirst(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.offerLast(null);
        });
        assertThrows(NullPointerException.class, () -> {
            deque.offer(null);
        });
        assertTrue(deque.offerFirst(5));
        assertEquals(1, deque.size());
        assertTrue(deque.offerLast(5));
        assertEquals(2, deque.size());
        assertTrue(deque.offer(5));
        assertEquals(3, deque.size());
    }

    @Test
    void dequeue() {
        assertNull(deque.dequeueFront());
        assertNull(deque.dequeueBack());
        deque.enqueueBack(1);
        deque.enqueueBack(2);
        assertEquals(2, deque.size());
        assertFalse(deque.isEmpty());
        assertEquals(2, deque.dequeueBack());
        assertEquals(1, deque.dequeueFront());
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
        assertThrows(NoSuchElementException.class, () -> {
            deque.remove();
        });
        for (int i = 0; i < 4; i++) deque.enqueueBack(i);
        assertEquals(4, deque.size());
        assertEquals(0, deque.remove());
        assertEquals(1, deque.removeFirst());
        assertEquals(3, deque.removeLast());
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test
    void poll() {
        assertNull(deque.poll());
        assertNull(deque.pollFirst());
        assertNull(deque.pollLast());
        for (int i = 0; i < 4; i++) deque.enqueueBack(i);
        assertEquals(4, deque.size());
        assertEquals(0, deque.poll());
        assertEquals(1, deque.pollFirst());
        assertEquals(3, deque.pollLast());
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());
    }

    @Test
    void element() {
        assertThrows(NoSuchElementException.class, () -> {
            deque.element();
        });
        deque.add(5);
        assertEquals(1, deque.size());
        assertEquals(5, deque.element());
        assertEquals(1, deque.size());
    }

    @Test
    @DisplayName("gets front element without removal")
    void front() {
        assertNull(deque.front());
        assertNull(deque.peek());
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
        assertEquals(1, deque.peek());
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

    @Test
    @DisplayName("supports stack methods")
    void stack() {
        assertNull(deque.pop());
        for (int i = 0; i < 3; i++) {
            assertTrue(deque.push(i));
        }
        assertEquals(2, deque.pop());
        assertEquals(1, deque.pop());
        assertEquals(0, deque.pop());
        assertNull(deque.pop());
    }
}
