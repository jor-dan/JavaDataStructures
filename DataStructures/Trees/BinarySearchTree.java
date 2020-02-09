package Trees;

/**
 * A binary search tree is a data structure where each node has at most
 * two child nodes (left and right). The left child is less than the parent and
 * the right child is greater than the parent. The tree is structured and sorted
 * such that the same concept behind binary search applies when searching it.
 * This gives balanced binary search trees an advantage in time complexity over
 * linear data structures like linked lists for most operations.
 * <p>
 * In this implementation, keys are mapped to values
 * and the keys are used to sort the tree.
 *
 * @author Jordan Owens
 * @param <K> the key's type
 * @param <V> the value's type
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    /** Implementation of the nodes that make up the tree */
    private static class Node<K, V> {
        K key;
        V value;
        /** Node's left and right children */
        Node<K, V> left, right;

        /**
         * Constructs a leaf node storing a key/value pair
         *
         * @param key the key to store in the node
         * @param value the value to store in the node
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    /** Root of the tree */
    private Node<K, V> root;
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
     * Inserts a key/value pair into the tree
     *
     * @param key the key to insert
     * @param value the value to associate with the key
     * @return {@code true} if the pair was successfully inserted
     *         {@code false} if the key already exists in the tree
     * @throws NullPointerException if the key or value is {@code null}
     */
    public boolean insert(K key, V value) {
        if (key == null || value == null) throw new NullPointerException();
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return true;
        }
        Node<K, V> prev = root;
        int comp = 0;
        for (Node<K, V> current = root; current != null;) {
            comp = key.compareTo(current.key);
            if (comp == 0) return false;
            prev = current;
            current = comp < 0 ? current.left : current.right;
        }
        if (comp < 0) prev.left = new Node<>(key, value);
        else prev.right = new Node<>(key, value);
        size++;
        return true;
    }

    /**
     * Inserts a key/value pair into the tree
     * or overwrites any existing key/value pair with the new value.
     * Deletes key/value pair if the new value is {@code null}.
     *
     * @param key the key to insert or update
     * @param value the new value
     * @throws NullPointerException if the key is {@code null}
     */
    public void put(K key, V value) {
        if (key == null) throw new NullPointerException();
        root = value != null ? put(root, key, value) : remove(root, key);
    }

    private Node<K, V> put(Node<K, V> root, K key, V value) {
        if (root == null) {
            this.size++;
            return new Node<>(key, value);
        }
        int comp = key.compareTo(root.key);
        if (comp < 0) {
            root.left = put(root.left, key, value);
        } else if (comp > 0) {
            root.right = put(root.right, key, value);
        } else {
            root.value = value;
        }
        return root;
    }

    /**
     * Removes a key/value pair from the tree
     * @param key the key of the key/value pair to remove
     * @return {@code true} if the pair was removed from the tree
     * @throws NullPointerException if the key is {@code null}
     */
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException();
        int oldSize = size;
        root = remove(root, key);
        return size == oldSize - 1;
    }

    private Node<K, V> remove(Node<K, V> root, K key) {
        if (root == null) return null;
        final int comp = key.compareTo(root.key);
        if (comp < 0) {
            root.left = remove(root.left, key);
        } else if (comp > 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                this.size--;
                return root.left == null ? root.right : root.left;
            }
            Node<K, V> min = root.right;
            while (min.left != null) {
                min = min.left;
            }
            root.key = min.key;
            root.right = remove(root.right, min.key);
        }
        return root;
    }

    /**
     * Gets the value mapped to a key in the tree
     *
     * @param key the key of the key/value pair
     * @return the value the key is associated with
     *         or {@code null} if the key does not exist in the tree
     * @throws NullPointerException if key is {@code null}
     */
    public V get(K key) {
        if (key == null) throw new NullPointerException();
        for (Node<K, V> current = root; current != null;) {
            final int comp = key.compareTo(current.key);
            if (comp == 0) return current.value;
            current = comp < 0 ? current.left : current.right;
        }
        return null;
    }

    /**
     * Returns whether a key/value pair exists in the tree
     *
     * @param key the key of the pair being searched for in the tree
     * @param value the value of the pair being searched for in the tree
     * @return {@code true} if the key/value pair is in the tree
     * @throws NullPointerException if the key or value is {@code null}
     */
    public boolean contains(K key, V value) {
        if (value == null) throw new NullPointerException();
        return value.equals(get(key));
    }

    /**
     * Returns whether a key exists in the tree
     *
     * @param key the key being searched for in the tree
     * @return {@code true} if the key is in the tree
     * @throws NullPointerException if the key is {@code null}
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Gets the smallest key in the tree
     *
     * @return the smallest key in the tree or
     *         {@code null} if the tree is empty
     */
    public K min() {
        if (root == null) return null;
        Node<K, V> min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min.key;
    }

    /**
     * Gets the largest key in the tree
     *
     * @return the largest key in the tree or
     *         {@code null} if the tree is empty
     */
    public K max() {
        if (root == null) return null;
        Node<K, V> max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max.key;
    }
}
