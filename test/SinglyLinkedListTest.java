import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Singly Linked List")
public class SinglyLinkedListTest {
    SinglyLinkedList<Integer> list;

    @BeforeEach
    void instantiate() {
        list = new SinglyLinkedList<>();
    }

    @Test
    void empty() {
        assertEquals(true, list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void insert() {
        list.insert(1);
        assertEquals(1, list.size());
        assertEquals(false, list.isEmpty());
        list.insert(2);
        assertEquals(2, list.size());
    }

    @Test
    void append() {
        list.insert(2);
        list.append(1);
        assertEquals(1, list.get(list.size() - 1));
    }

    @Test
    void get() {
        list.insert(1);
        assertEquals(1, list.get(0));
        assertEquals(null, list.get(-1));
        assertEquals(null, list.get(list.size()));
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
}