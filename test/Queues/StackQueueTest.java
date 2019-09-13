package Queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StackQueue")
public class StackQueueTest {
    StackQueue<Integer> sq;

    @BeforeEach
    void instantiate() {
        sq = new StackQueue<>();
    }

    @Test
    void empty() {
        assertTrue(sq.isEmpty());
        assertEquals(0, sq.size());
    }

    @Test
    void enqueue() {
        assertThrows(NullPointerException.class, () -> {
            sq.enqueue(null);
        });
        sq.enqueue(1);
        assertFalse(sq.isEmpty());
        assertEquals(1, sq.size());
        sq.enqueue(2);
        sq.enqueue(3);
        sq.enqueue(4);
        assertEquals(4, sq.size());
    }

    @Test
    void dequeue() {
        assertNull(sq.dequeue());
        sq.enqueue(1);
        sq.enqueue(2);
        sq.enqueue(3);
        sq.enqueue(4);
        assertEquals(4, sq.size());
        assertFalse(sq.isEmpty());
        assertEquals(1, sq.dequeue());
        assertEquals(2, sq.dequeue());
        assertEquals(3, sq.dequeue());
    }

    @Test
    void peek() {
        assertNull(sq.peek());
        sq.enqueue(1);
        sq.enqueue(2);
        sq.enqueue(3);
        sq.enqueue(4);
        assertEquals(1, sq.peek());
        sq.dequeue();
        assertEquals(2, sq.peek());
        assertFalse(sq.isEmpty());
        sq.enqueue(10);
        sq.dequeue();
        assertEquals(3, sq.peek());
        while (!sq.isEmpty()) sq.dequeue();
        assertNull(sq.peek());
    }
}
