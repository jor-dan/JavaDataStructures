import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Stack")
public class StackTest {
    Stack<Integer> stack;

    @BeforeEach
    void instantiate() {
        stack = new Stack<>();
    }

    @Test
    void empty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void push() {
        assertThrows(NullPointerException.class, () -> {
            stack.push(null);
        });
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        stack.push(2);
        assertEquals(2, stack.size());
    }

    @Test
    void pop() {
        assertNull(stack.pop());
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(1, stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void top() {
        assertNull(stack.top());
        stack.push(1);
        assertEquals(1, stack.top());
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.top());
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.top());
        assertEquals(1, stack.size());
        stack.pop();
        assertNull(stack.top());
        assertTrue(stack.isEmpty());
    }
}
