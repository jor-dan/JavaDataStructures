import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Doubly Linked List")
public class DoublyLinkedListTest {
    DoublyLinkedList<Integer> list;

    @BeforeEach
    void instantiate() {
        list = new DoublyLinkedList<>();
    }

    @Test
    void empty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void insert() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.insert(null);
        });
        list.insert(1);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        list.insert(2);
        assertEquals(2, list.size());
    }

    @Test
    void delete() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.delete(null);
        });
        assertFalse(list.delete(1));
        list.insert(1);
        assertTrue(list.delete(1));
        for (int i = 1; i <= 5; i++) list.insert(i);
        assertFalse(list.delete(6));
        assertTrue(list.delete(2));
        assertTrue(list.delete(3));
        assertTrue(list.delete(1));
        assertTrue(list.delete(5));
    }

    @Test
    void get() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
        list.insert(1);
        list.insert(2);
        list.insert(3);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(list.size());
        });
    }

    @Test
    void reverse() {
        int[] elements = { 1, 2, 3, 4, 5 };
        for (int num : elements) {
            list.insert(num);
        }
        list.reverse();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(elements[i], list.get(list.size() - i - 1));
        }
    }
}