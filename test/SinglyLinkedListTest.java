import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Singly Linked List")
public class SinglyLinkedListTest {
    SinglyLinkedList<Integer> list;

    @BeforeEach
    void instantiate() {
        list = new SinglyLinkedList<>();
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
    void append() {
        assertThrows(IllegalArgumentException.class, () -> {
           list.append(null);
        });
        list.append(1);
        list.append(2);
        assertEquals(2, list.get(list.size() - 1));
    }

    @Test
    void delete() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.delete(null);
         });
        assertFalse(list.delete(1));
        list.insert(1);
        list.append(2);
        list.append(3);
        assertFalse(list.delete(4));
        assertTrue(list.delete(2));
        assertTrue(list.delete(1));
        assertEquals(1, list.size());
    }

    @Test
    void get() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
        list.insert(1);
        assertEquals(1, list.get(0));
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
            list.append(num);
        }
        list.reverse();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(elements[i], list.get(list.size() - i - 1));
        }
    }

    @Test
    void contains() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.contains(null);
        });
        assertFalse(list.contains(0));
        list.append(0);
        list.append(1);
        assertTrue(list.contains(1));
    }

    @Test
    void indexOf() {
        assertThrows(IllegalArgumentException.class, () -> {
           list.indexOf(null);
        });
        assertEquals(-1, list.indexOf(0));
        list.append(0);
        list.append(1);
        assertEquals(1, list.indexOf(1));
    }
}