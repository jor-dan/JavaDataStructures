package LinkedLists;

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
    @DisplayName("Instantiate with array of elements")
    void instatiateWithArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DoublyLinkedList<>(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            String[] containsNull = {"1", null};
            new DoublyLinkedList<>(containsNull);
        });
        String[] arr = {"1", "2", "3"};
        DoublyLinkedList<String> arrList = new DoublyLinkedList<>(arr);
        assertFalse(arrList.isEmpty());
        assertEquals(3, arrList.size());
        assertEquals("1", arrList.get(0));
        assertEquals("2", arrList.get(1));
        assertEquals("3", arrList.get(2));
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

    @Test
    void contains() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.contains(null);
        });
        assertFalse(list.contains(0));
        list.insert(0);
        list.insert(1);
        assertTrue(list.contains(1));
    }

    @Test
    void indexOf() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.indexOf(null);
        });
        assertEquals(-1, list.indexOf(0));
        list.insert(0);
        list.insert(1);
        assertEquals(1, list.indexOf(1));
    }
}
