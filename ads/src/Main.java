import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // --- Hash Table ---
        MyHashTable<MyTestingClass, String> hashTable = new MyHashTable<>(137);
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(i, "Name" + random.nextInt(10000));
            hashTable.put(key, "Student" + i);
        }

        System.out.println("📊 Elements per bucket:");
        var buckets = hashTable.getChainArray();
        for (int i = 0; i < buckets.length; i++) {
            int count = 0;
            var current = buckets[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count);
        }

        // --- Binary Search Tree ---
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();

        for (int i = 0; i < 20; i++) {
            int key = random.nextInt(100);
            bst.insert(key, "Value" + key);
        }

        System.out.println("\n🌳 BST In-Order Traversal:");
        for (var entry : bst) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println("BST Size: " + bst.size());
    }
}
