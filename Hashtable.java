import java.util.*;
import java.lang.*;
public class Hashtable<k, v> {

    class HashNode<k, v> {
        public String key;
        public String value;
        public HashNode next;

        public HashNode(String key, String value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    private HashNode[] buckets; // hashtable of buckets
    private int num_buckets; // size of table
    private int size; // number of items

    public Hashtable(){ // initializes all the variables
        num_buckets = 250000;
        buckets = new HashNode[num_buckets];
        size = 0;
    }

    public int getBucket(String key) {
        int hashCode = key.hashCode(); // uses hashCode to create the hashCode
        return Math.abs(hashCode % num_buckets); // bucket location determined with the use of modulus
    }

    public String get(String key) {
        int bucketLocationInArray = getBucket(key);
        HashNode node = buckets[bucketLocationInArray];
        while (node != null) {
            if (node.key.equals(key))
                return node.value; // returns the value of the stored item
            node = node.next; // traverses the list
        }
        return null;
    }
    public boolean containsKey(String key) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        while (node != null) {
            if (node.key.equals(key)) // checks if the key exists to return true
                return true;
            node = node.next; // traverses the list
        }
        return false;
    }


    // check if key exists, if it does, replace the value, if not add value
    public void put(String key, String value) {
        int bucketLocationInArray = getBucket(key);
        HashNode node = buckets[bucketLocationInArray];
        while (node != null) {
            if (node.key.equals(key)) {
                node.key = key;
                node.value = value;
                return;
            }
            node = node.next; // traverses the list
        }
        
        // creates a new node to the hashtable
        ++size;
        HashNode newNode = new HashNode(key, value); // create new node to store item
        newNode.next = buckets[bucketLocationInArray]; // set next equal to head
        buckets[bucketLocationInArray] = newNode; // set head/location equal to new node
    }

    public String remove(String key) {
        int bucketLocationInArray = getBucket(key);
        HashNode node = buckets[bucketLocationInArray];
        HashNode prev = null;
        while (node != null && !node.key.equals(key)){ // traversing the list
            prev = node;
            node = node.next;
        }
        if (node.key.equals(key))
        {
            if (prev == null) {
                buckets[bucketLocationInArray] = node.next; // removes the head
                --size;
            }
            else {
                prev.next = node.next; // removes middle/end node
                --size;
            }
        }
        return node.value; // returns the value
    }
}