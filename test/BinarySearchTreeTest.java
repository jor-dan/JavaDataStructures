import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Binary Search Tree")
public class BinarySearchTreeTest {

    BinarySearchTree<Integer> tree;

    @BeforeEach
    void instantiate() {
        tree = new BinarySearchTree<>();
    }

    @Test
    void empty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void insert() {
        assertThrows(NullPointerException.class, () -> {
            tree.insert(null);
        });
        assertTrue(tree.insert(5));
        assertFalse(tree.insert(5));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        int[] numbers = { 3, 4, 2, 7, 6, 8 };
        for (int number : numbers) {
            assertTrue(tree.insert(number));
        }
        assertEquals(numbers.length + 1, tree.size());
    }

    @Test
    void remove() {
        assertThrows(NullPointerException.class, () -> {
            tree.remove(null);
        });
        assertFalse(tree.remove(0));
        tree.insert(1);
        assertTrue(tree.remove(1));
        assertFalse(tree.remove(987654321));
        assertEquals(0, tree.size());
        int[] numbers = { 15, 5, 3, 2, 10, 12, 20, 30, 29, 28, 27 };
        for (int number : numbers) {
            tree.insert(number);
        }
        assertTrue(tree.remove(20));
        assertTrue(tree.remove(15));
        assertTrue(tree.remove(10));
        assertTrue(tree.remove(3));
    }

    @Test
    void contains() {
        assertThrows(NullPointerException.class, () -> {
            tree.contains(null);
        });
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(15));
    }

    @Test
    void min() {
        assertNull(tree.min());
        tree.insert(2);
        assertEquals(2, tree.min());
        tree.insert(3);
        assertEquals(2, tree.min());
        tree.insert(1);
        assertEquals(1, tree.min());
        tree.insert(0);
        assertEquals(0, tree.min());
    }

    @Test
    void max() {
        assertNull(tree.max());
        tree.insert(2);
        assertEquals(2, tree.max());
        tree.insert(1);
        assertEquals(2, tree.max());
        tree.insert(3);
        assertEquals(3, tree.max());
        tree.insert(4);
        assertEquals(4, tree.max());
        
    }
}
