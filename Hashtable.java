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

    HashNode[] buckets;
    int num_buckets; // size of table
    int size; // number of items

    public Hashtable(){
        num_buckets = 250000;
        buckets = new HashNode[num_buckets];
        size = 0;
    }

    public int getBucket(String key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % num_buckets);
    }

// hashtable cannot have any null keys, hashmap can have one null key

    public String get(String key) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        while (node != null) {
            if (node.key.equals(key))
                return node.value;
            node = node.next;
        }
        return null;
    }
    public boolean containsKey(String key) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        while (node != null) {
            if (node.key.equals(key))
                return true;
            node = node.next;
        }
        return false;
    }


    // check if key exists, if it does, replace the value, if not add value
    public void put(String key, String value) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        while (node != null) {
            if (node.key.equals(key)) {
                node.key = key;
                node.value = value;
                return;
            }
            node = node.next;
        }

        ++size;
        HashNode newNode = new HashNode(key, value);
        newNode.next = buckets[bucket_id];
        buckets[bucket_id] = newNode;

//        if (1.0 * size / 1.0 * num_buckets > 0.5)
//        {
//            growBucky();
//        }
    }

//    public void growBucky()
//    {
//        ArrayList<HashNode> newBucket = new ArrayList<>(size*2);
//        for (int i = 0; i < buckets.size(); i++)
//        {
//            HashNode node = buckets.get(i);
//            while(node != null)
//            {
//                HashNode next = node.next;
//            }
//        }
//        ArrayList<HashNode> newbuckets;
//        for (int i = 0; i <buckets.size(); i++) {
//            HashNode node = buckets.get(i);
//            while (node != null) {
//                HashNode next = node.next;
//                int hashcode = (Math.abs(node.key.hashCode())) % newbuckets.size();
//                node.next = buckets.get(i);
//            }
//        }

//        ListNode[] newtable = new ListNode[table.length*2];
//        for (int i = 0; i < table.length; i++) {
//            ListNode list = table[i];
//            while (list != null) {
//                ListNode next = list.next;
//                int hash = (Math.abs(list.key.hashCode())) % newtable.length;
//                list.next = newtable[hash];
//                newtable[hash] = list;
//                list = next;
//            }
//        }
//        table = newtable;
//    }
//    }
    public String remove(String key) {
        int bucket_id = getBucket(key);
        HashNode node = buckets[bucket_id];
        HashNode prev = null;
        while (node != null && !node.key.equals(key)){
            prev = node;
            node = node.next;
        }
        if (node.key.equals(key))
        {
            if (prev == null) {
                buckets[bucket_id] = node.next;
                --size;
            }
            else {
                prev.next = node.next;
                --size;
            }
        }
        return node.value;
    }
}