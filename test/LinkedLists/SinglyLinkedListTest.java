package LinkedLists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Singly Linked List")
class SinglyLinkedListTest {
    SinglyLinkedList<Integer> list;

    @BeforeEach
    void instantiate() {
        list = new SinglyLinkedList<>();
    }

    @Test
    @DisplayName("should instantiate from array of elements")
    void instantiateWithArray() {
        assertThrows(NullPointerException.class,
                () -> new SinglyLinkedList<>(null));
        assertThrows(NullPointerException.class, () -> {
            String[] containsNull = {"1", null};
            new SinglyLinkedList<>(containsNull);
        });
        String[] arr = {"1", "2", "3"};
        SinglyLinkedList<String> arrList = new SinglyLinkedList<>(arr);
        assertFalse(arrList.isEmpty());
        assertEquals(3, arrList.size());
        assertEquals("1", arrList.get(0));
        assertEquals("2", arrList.get(1));
        assertEquals("3", arrList.get(2));
    }

    @Test
    @DisplayName("should be empty after instantiation")
    void empty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("should add elements to list")
    void add() {
        assertThrows(NullPointerException.class, () -> list.insert(null));
        assertThrows(NullPointerException.class, () -> list.add(null));
        assertThrows(NullPointerException.class, () -> list.add(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(list.size() + 1, 1));
        // Inserts to front of list
        list.insert(1);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        list.insert(2);
        assertEquals(2, list.size());
        // Appends to end of list
        list = new SinglyLinkedList<>();
        assertThrows(NullPointerException.class, () -> list.add(null));
        list.add(1);
        list.add(2);
        assertEquals(2, list.get(list.size() - 1));
        // Adds at specific index in list
        assertDoesNotThrow(() -> list.add(0, 0));
        assertEquals(0, list.get(0));
        assertEquals(3, list.size());
        assertDoesNotThrow(() -> list.add(1, 0));
        assertEquals(0, list.get(1));
        assertEquals(4, list.size());
        assertDoesNotThrow(() -> list.add(list.size(), 0));
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("should remove elements from list")
    void remove() {
        assertThrows(NullPointerException.class, () -> list.remove(null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        // Remove specific element
        Integer[] elements = { 1, 2, 3, 4 };
        assertFalse(list.remove(elements[0]));
        for (Integer z : elements) list.add(z);
        assertFalse(list.remove((Integer)(-1)));
        assertTrue(list.remove(elements[2]));
        assertTrue(list.remove(elements[3]));
        assertTrue(list.remove(elements[0]));
        assertTrue(list.remove(elements[1]));
        // Remove element at index
        for (int i = 0; i < 4; i++) list.add(i);
        assertEquals(0, list.remove(0));
        assertEquals(2, list.remove(1));
        assertEquals(3, list.remove(1));
        assertEquals(1, list.remove(0));
    }

    @Test
    @DisplayName("should replace the element at an index")
    void set() {
        assertThrows(NullPointerException.class, () -> list.set(0, null));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 0));
        list.insert(0);
        assertEquals(0, list.set(0, 1));
        assertEquals(1, list.get(0));
    }

    @Test
    @DisplayName("should get elements by index")
    void get() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        list.insert(1);
        assertEquals(1, list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(list.size()));
    }

    @Test
    @DisplayName("should reverse list")
    void reverse() {
        int[] elements = { 1, 2, 3, 4, 5 };
        for (int num : elements) {
            list.add(num);
        }
        list.reverse();
        for (int i = 0; i < list.size(); i++) {
            assertEquals(elements[i], list.get(list.size() - i - 1));
        }
    }

    @Test
    @DisplayName("should return whether element exists in list")
    void contains() {
        assertThrows(NullPointerException.class, () -> list.contains(null));
        assertFalse(list.contains(0));
        list.add(0);
        list.add(1);
        assertTrue(list.contains(1));
    }

    @Test
    @DisplayName("should return index of element in list")
    void indexOf() {
        assertThrows(NullPointerException.class, () -> list.indexOf(null));
        assertEquals(-1, list.indexOf(0));
        list.add(0);
        list.add(1);
        assertEquals(1, list.indexOf(1));
    }
}
