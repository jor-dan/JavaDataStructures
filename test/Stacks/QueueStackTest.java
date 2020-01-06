package Stacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("QueueStack")
public class QueueStackTest {
    QueueStack<Integer> qs;

    @BeforeEach
    void instantiate() {
        qs = new QueueStack<>();
    }

    @Test
    void empty() {
        assertTrue(qs.isEmpty());
        assertEquals(0, qs.size());
    }

    @Test
    void push() {
        assertThrows(NullPointerException.class, () -> {
            qs.push(null);
        });
        assertEquals(0, qs.size());
        for (int i = 1; i <= 5; i++) {
            qs.push(i);
            assertEquals(i, qs.size());
        }
    }

    @Test
    void pop() {
        assertNull(qs.pop());
        qs.push(1);
        qs.push(2);
        assertEquals(2, qs.size());
        assertEquals(2, qs.pop());
        assertEquals(1, qs.size());
        assertEquals(1, qs.pop());
        assertEquals(0, qs.size());
        assertTrue(qs.isEmpty());
    }

    @Test
    void top() {
        assertNull(qs.top());
        qs.push(1);
        assertEquals(1, qs.top());
        assertEquals(1, qs.size());
        qs.push(2);
        assertEquals(2, qs.top());
        assertEquals(2, qs.size());
        qs.pop();
        assertEquals(1, qs.top());
        assertEquals(1, qs.size());
        qs.pop();
        assertNull(qs.top());
        assertTrue(qs.isEmpty());
    }
}
