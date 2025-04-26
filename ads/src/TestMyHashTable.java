import java.util.Random;
import java.util.LinkedList;

public class TestMyHashTable {
    public static <K, V> void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(10000);
            String name = "Name" + id;
            MyTestingClass key = new MyTestingClass(id, name);
            Student value = new Student(name, id);
            table.put(key, value);
        }

        System.out.println("Total size: " + table.size());

        for (int i = 0; i < table.getBucketCount(); i++) {
            int bucketSize = 0;
            MyHashTable.HashNode<K, V> current = (MyHashTable.HashNode<K, V>) table.getChainArray()[i];

            while (current != null) {
                bucketSize++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + bucketSize + " elements");
        }
    }
}