public class Hashtable {
    int capacity;
    String[] table;


    public Hashtable(int size) {
        // Add code here for your constructor
        this.capacity = size;
        this.table = new String[size];
    }

    public int hashValue(String key) {
        // calculates the index using a hashValue and hashFunction
        int hashCode = key.hashCode();
        return (hashCode & 0x7fffffff) % this.capacity;
    }

    public void add(String key) {
        // Add code here for your add method, using linear probing to resolve collisions
        int index = hashValue(key);
        for (int i = 0; i < this.capacity; i++) {
            int linearProbeIndex = (index + i) % this.capacity;
            if (key.equals(this.table[linearProbeIndex])) { return; }
            if (this.table[linearProbeIndex] == null) {
                this.table[linearProbeIndex] = key;
                return;
            }
        }
    }

    private int stringSearch(String key) {
        // Searches for string and returns its index, returns -1 if not found
        int index = hashValue(key);
        for (int i = 0; i < this.capacity; i++) {
            int linearProbeIndex = (index + i) % this.capacity;
            if (key.equals(this.table[linearProbeIndex])) { return linearProbeIndex; }
        }
        return -1;
    }

    public boolean search(String key) {
        // Returns true if key is in the table
        return stringSearch(key) >= 0;
    }

    public void delete(String key) {
        // Deletes the key from the table and rehashes
        int index = stringSearch(key);
        if (index >= 0) {
            this.table[index] = null;
            rehash(index);
        }
    }

    private void rehash(int startIndex) {
        // rehashes the hash table
        String[] temp = new String[this.capacity];
        System.arraycopy(this.table, 0, temp, 0, this.capacity);

        this.table = new String[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            int linearProbeIndex = (startIndex + i) % this.capacity;
            if (temp[linearProbeIndex] != null) {
                this.add(temp[linearProbeIndex]);
            }
        }
    }

    public String get(int i) {
        // Add code here for your get method
        return this.table[i];
    }
}
