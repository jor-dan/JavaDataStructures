/**
 * A binary search tree is a data structure where each node has at most
 * two child nodes (left and right). The left child is less than the parent and
 * the right child is greater than the parent. The tree is structured and sorted
 * such that the same concept behind binary search applies when searching it.
 * This gives balanced binary search trees an advantage in time complexity over
 * linear data structures like linked lists for most operations.
 *
 * @author Jordan Owens
 * @param <T> the type of elements in the binary search tree
 */
public class BinarySearchTree<T extends Comparable<T>> {
    /** Implementation of the nodes that make up the tree */
    private static class Node<T> {
        /** Element the node stores */
        T element;
        /** The node's left child */
        Node<T> left;
        /** The node's right child */
        Node<T> right;

        /**
         * Constructs a node storing an element
         *
         * @param element the element to store in the node
         */
        Node(T element) {
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }

    /** Root of the tree */
    private Node<T> root;
    /** Number of elements in the tree */
    private int size;

    /** Constructs an empty binary search tree */
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Gets the size of the tree
     *
     * @return the number of elements in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree is empty or not
     *
     * @return {@code true} if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserts an element into the tree
     *
     * @param element the element to insert
     * @return {@code true} if the element was successfully inserted
     *         {@code false} if the element already exists in the tree
     * @throws NullPointerException if the element is null
     */
    public boolean insert(T element) {
        if (element == null) throw new NullPointerException();
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        for (Node<T> current = root; current != null;) {
            final int comp = element.compareTo(current.element);
            if (comp < 0) {
                if (current.left == null) {
                    current.left = new Node<>(element);
                    size++;
                    return true;
                }
                current = current.left;
            } else if (comp > 0) {
                if (current.right == null) {
                    current.right = new Node<>(element);
                    size++;
                    return true;
                }
                current = current.right;
            } else current = null;
        }
        return false;
    }

    /**
     * Removes an element from the tree
     * @param element the element to remove
     * @return {@code true} if the element was removed from the tree
     * @throws NullPointerException if the element is null
     */
    public boolean remove(T element) {
        if (element == null) throw new NullPointerException();
        int oldSize = size;
        root = remove(root, element);
        return size == oldSize - 1;
    }

    private Node<T> remove(Node<T> root, T element) {
        if (root == null) return null;
        final int comp = element.compareTo(root.element);
        if (comp < 0) {
            root.left = remove(root.left, element);
        } else if (comp > 0) {
            root.right = remove(root.right, element);
        } else {
            if (root.left == null || root.right == null) {
                this.size--;
                return root.left == null ? root.right : root.left;
            }
            Node<T> min = root.right;
            while (min.left != null) {
                min = min.left;
            }
            root.element = min.element;
            root.right = remove(root.right, min.element);
        }
        return root;
    }

    /**
     * Returns whether an element exists in the tree
     *
     * @param data element being searched for in the tree
     * @return {@code true} if the element is in the tree
     * @throws NullPointerException if data is null
     */
    public boolean contains(T element) {
        if (element == null) throw new NullPointerException();
        for (Node<T> current = root; current != null;) {
            final int comp = element.compareTo(current.element);
            if (comp == 0) return true;
            current = comp < 0 ? current.left : current.right;
        }
        return false;
    }
}
