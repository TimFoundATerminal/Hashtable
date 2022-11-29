public class Hashtable {
    double loadFactor = 0.0;
    int capacity;
    Object[] table;


    public Hashtable(int size) {
        // Add code here for your constructor
        this.capacity = size;
        this.table = new Object[size];
    }

    public int hashValue(String key) {
        // calculates the index using a hashValue and hashFunction
        int hashCode = key.hashCode();
        return (hashCode & 0x7fffffff) % capacity;
    }

    public void add(String key) {
        // Add code here for your add method, using linear probing to resolve collisions
    }

    public boolean search(String key) {
        // Add code here for your search method
        return false;
    }

    public void delete(String key) {
        // Add code here for your delete method
    }

    public String get(int i) {
        // Add code here for your get method
        return null;
    }

}
