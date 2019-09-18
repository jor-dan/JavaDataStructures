package Trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AVL Tree")
public class AVLTreeTest {
    AVLTree<Integer, String> tree;

    @BeforeEach
    void instatiate() {
        tree = new AVLTree<>();
    }

    @Test
    void empty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void insert() {
        assertThrows(NullPointerException.class, () -> {
            tree.insert(null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            tree.insert(null, "1");
        });
        assertThrows(NullPointerException.class, () -> {
            tree.insert(1, null);
        });
        assertTrue(tree.insert(5, "A"));
        assertFalse(tree.insert(5, "A"));
        assertFalse(tree.insert(5, "B"));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        int[] numbers = { 4, 3, 0, 2, 7, 6, 8 };
        for (int number : numbers) {
            assertTrue(tree.insert(number, ""));
        }
        assertEquals(numbers.length + 1, tree.size());
    }

    @Test
    void put() {
        assertThrows(NullPointerException.class, () -> {
            tree.put(null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            tree.put(1, null);
        });
        assertThrows(NullPointerException.class, () -> {
            tree.put(null, "1");
        });
        int[] numbers = { 4, 3, 2, 7, 6, 8 };
        for (int number : numbers) {
            tree.insert(number, Integer.toString(number));
        }
        assertEquals(numbers.length, tree.size());
        assertEquals("7", tree.get(7));
        tree.put(7, "07");
        assertEquals(numbers.length, tree.size());
        assertEquals("07", tree.get(7));
        tree.put(2, "02");
        assertEquals(numbers.length, tree.size());
        assertEquals("02", tree.get(2));
        tree.put(123, "123");
        assertEquals(numbers.length + 1, tree.size());
        assertEquals("123", tree.get(123));
    }

    @Test
    void remove() {
        assertThrows(NullPointerException.class, () -> {
            tree.remove(null);
        });
        assertFalse(tree.remove(0));
        tree.insert(1, "1");
        assertTrue(tree.remove(1));
        assertFalse(tree.remove(987654321));
        assertEquals(0, tree.size());
        for (int i = 0; i < 20; i++) {
            tree.insert(i, "");
        }
        assertFalse(tree.remove(20));
        assertTrue(tree.remove(15));
        assertTrue(tree.remove(2));
        assertTrue(tree.remove(1));
        assertTrue(tree.remove(18));
    }

    @Test
    void get() {
        assertThrows(NullPointerException.class, () -> {
            tree.get(null);
        });
        assertNull(tree.get(1));
        tree.insert(5, "5");
        tree.insert(1, "1");
        tree.insert(10, "10");
        assertEquals("5", tree.get(5));
        assertEquals("1", tree.get(1));
        assertEquals("10", tree.get(10));
    }

    @Test
    void contains() {
        assertThrows(NullPointerException.class, () -> {
            tree.contains(null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            tree.contains(1, null);
        });
        tree.insert(10, "10");
        tree.insert(20, "20");
        tree.insert(5, "5");
        assertTrue(tree.contains(20, "20"));
        assertFalse(tree.contains(20, "020"));
        assertTrue(tree.contains(5, "5"));
        assertFalse(tree.contains(15, "15"));
    }

    @Test
    void containsKey() {
        assertThrows(NullPointerException.class, () -> {
            tree.containsKey(null);
        });
        tree.insert(10, "10");
        tree.insert(20, "20");
        tree.insert(5, "5");
        assertTrue(tree.containsKey(20));
        assertTrue(tree.containsKey(5));
        assertFalse(tree.containsKey(15));
    }
}
