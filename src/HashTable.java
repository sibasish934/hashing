public class HashTable {
    private HashNode []buckets;
    private int noOfBuckets;
    private int size;

    public HashTable(){
        this(10);
    }

    public HashTable(int capacity){
        buckets = new HashNode[capacity];
        this.noOfBuckets = capacity;
        this.size = 0;
    }

    private class HashNode{
        private Integer key;
        private String value;
        private HashNode next;

        public HashNode(Integer key, String value){
            this.key =key;
            this.value= value;
            this.next = null;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void put(Integer key, String value){
        if(key == null || value == null){
            throw  new IllegalArgumentException("Key or value is null");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        while(head != null){
            if(head.key.equals(key)){
                 head.value = value;
                 return;
            }
            head = head.next;
        }

        size++;
        head = buckets[bucketIndex];
        HashNode newNode = new HashNode(key,value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }

    public String getKey(Integer key){
        if(key == null){
            return new String("Key is null");
        }
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        while (head != null) {
            if(head.key.equals(key)){
               return head.value;
            }
            head = head.next;
        }
        return new String("Key does not exit");
    }

    private int getBucketIndex(Integer key) {
        return key % noOfBuckets;
    }

    public String removeKey(Integer key){
        if(key == null){
            return null;
        }
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        HashNode prev = null;

        while(head != null){
            if(head.key.equals(key)){
                break;
            }
            prev = head;
            head = head.next;
        }
        size--;
        if(head == null){
            return null;
        }
        if(prev != null){
            prev.next = head.next;
        }else{
            buckets[bucketIndex] = head.next;
        }
        return head.value;
    }

    public static void main(String[] args) {
        HashTable table = new HashTable(10);
        table.put(21,"sana");
        table.put(105,"rahul");
        table.put(105,"samurai");
        table.put(105,"Raj");
        String str = table.getKey(21);
        String str1 = table.getKey(32);
        System.out.println(table.size());
        System.out.println(str+"\n"+str1);
        System.out.println(table.removeKey(105));
        System.out.println(table.size());
    }

}
