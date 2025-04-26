import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V> implements Iterable<BinarySearchTree.Pair<K, V>> {
    private TreeNode rootNode;
    private int count = 0;

    private class TreeNode {
        K key;
        V value;
        TreeNode leftChild, rightChild;

        TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public void insert(K key, V value) {
        rootNode = insert(rootNode, key, value);
    }

    private TreeNode insert(TreeNode node, K key, V value) {
        if (node == null) {
            count++;
            return new TreeNode(key, value);
        }

        int comparison = key.compareTo(node.key);
        if (comparison < 0) {
            node.leftChild = insert(node.leftChild, key, value);
        } else if (comparison > 0) {
            node.rightChild = insert(node.rightChild, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    public V find(K key) {
        TreeNode node = rootNode;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.leftChild;
            } else if (cmp > 0) {
                node = node.rightChild;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        rootNode = remove(rootNode, key);
    }

    private TreeNode remove(TreeNode node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = remove(node.leftChild, key);
        } else if (cmp > 0) {
            node.rightChild = remove(node.rightChild, key);
        } else {
            count--;
            if (node.leftChild == null) return node.rightChild;
            if (node.rightChild == null) return node.leftChild;

            TreeNode temp = node;
            node = findMin(temp.rightChild);
            node.rightChild = removeMin(temp.rightChild);
            node.leftChild = temp.leftChild;
        }
        return node;
    }

    private TreeNode removeMin(TreeNode node) {
        if (node.leftChild == null) return node.rightChild;
        node.leftChild = removeMin(node.leftChild);
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        if (node.leftChild == null) return node;
        return findMin(node.leftChild);
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        List<Pair<K, V>> entries = new ArrayList<>();
        inOrderTraversal(rootNode, entries);
        return entries.iterator();
    }

    private void inOrderTraversal(TreeNode node, List<Pair<K, V>> entries) {
        if (node == null) return;

        inOrderTraversal(node.leftChild, entries);
        entries.add(new Pair<>(node.key, node.value));
        inOrderTraversal(node.rightChild, entries);
    }
}
