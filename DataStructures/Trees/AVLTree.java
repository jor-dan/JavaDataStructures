package Trees;

/**
 * AVL Trees are self-balancing Binary Search Trees.
 * In an AVL Tree the heights of any node's left and right subtrees
 * differ by at most 1.
 *
 * @author Jordan Owens
 * @param <K> the key's type
 * @param <V> the value's type
 */
public class AVLTree<K extends Comparable<K>, V> {
    private static class Node<K, V> {
        K key;
        V value;
        /** Node's left and right children */
        Node<K, V> left, right;
        /** Height of the node's subtree */
        int height;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    /** Root of the tree */
    Node<K, V> root;
    /** Number of elements in the tree */
    private int size;

    /** Constructs an empty tree */
    public AVLTree() {
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
     * Get the height of a subtree
     *
     * @param root the root of the subtree to find the height of
     * @return the height of the subtree with root {@code root}
     */
    private static int height(Node<?, ?> root) {
        return root != null ? root.height : 0;
    }

    private static int balance(Node<?, ?> root) {
        return height(root.left) - height(root.right);
    }

    private Node<K, V> rotateLeft(Node<K, V> pivot) {
        Node<K, V> right = pivot.right;
        Node<K, V> rightL = right.left;
        right.left = pivot;
        pivot.right = rightL;
        pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
        right.height = 1 + Math.max(height(right.left), height(right.right));
        return right;
    }

    private Node<K, V> rotateRight(Node<K, V> pivot) {
        Node<K, V> left = pivot.left;
        Node<K, V> leftR = left.right;
        left.right = pivot;
        pivot.left = leftR;
        pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
        left.height = 1 + Math.max(height(left.left), height(left.right));
        return left;
    }

    /**
     * Balances an unbalanced subtree
     *
     * @param root the root of the subtree to balance
     * @return the root of the balanced subtree
     */
    private Node<K, V> balanceTree(Node<K, V> root) {
        int balance = balance(root);
        // Right rotation
        if (balance > 1) {
            // Left-right rotation
            if (balance(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            root = rotateRight(root);
        }
        // Left rotation
        else if (balance < -1) {
            // Right-left rotation
            if (balance(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            root = rotateLeft(root);
        } 
        return root;
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
        int prevSize = size;
        root = insert(root, key, value);
        return size == prevSize + 1;
    }

    private Node<K, V> insert(Node<K, V> root, K key, V value) {
        if (root == null) {
            this.size++;
            return new Node<>(key, value);
        }
        int comp = key.compareTo(root.key);
        if (comp < 0) {
            root.left = insert(root.left, key, value);
        } else if (comp > 0) {
            root.right = insert(root.right, key, value);
        } else {
            return root;
        }
        root.height = 1 + Math.max(height(root.left), height(root.right));
        return balanceTree(root);
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
        if (value == null) remove(key);
        else if (!insert(key, value)) get(root, key).value = value;
    }

    /**
     * Removes a key/value pair from the tree
     *
     * @param key the key of the key/value pair to remove
     * @return {@code true} if the pair was removed from the tree
     * @throws NullPointerException if the key is {@code null}
     */
    public boolean remove(K key) {
        if (key == null) throw new NullPointerException();
        int prevSize = size;
        root = remove(root, key);
        return size == prevSize - 1;
    }

    private Node<K, V> remove(Node<K, V> root, K key) {
        if (root == null) return null;
        int comp = key.compareTo(root.key);
        if (comp < 0) {
            root.left = remove(root.left, key);
        } else if (comp > 0) {
            root.right = remove(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                this.size--;
                root = root.left == null ? root.right : root.left;
            } else {
                Node<K, V> min = root.right;
                while (min.left != null) {
                    min = min.left;
                }
                root.key = min.key;
                root.right = remove(root.right, min.key);
            }
        }
        if (root == null) return null;
        root.height = 1 + Math.max(height(root.left), height(root.right));
        return balanceTree(root);
    }

    private Node<K, V> get(Node<K, V> root, K key) {
        if (key == null) throw new NullPointerException(); 
        for (Node<K, V> current = root; current != null;) {
            int comp = key.compareTo(current.key);
            if (comp == 0) return current;
            current = comp < 0 ? current.left : current.right;
        }
        return null;
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
        Node<K, V> node = get(root, key);
        return node != null ? node.value : null;
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
        Node<K, V> node = get(root, key);
        return node != null && value.equals(node.value);
    }

    /**
     * Returns whether a key exists in the tree
     *
     * @param key the key being searched for in the tree
     * @return {@code true} if the key is in the tree
     * @throws NullPointerException if the key is {@code null}
     */
    public boolean containsKey(K key) {
        return get(root, key) != null;
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
