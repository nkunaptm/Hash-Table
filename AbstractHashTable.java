package dictionary;
import java.util.List;
/**
 * Abstract implementation of dictionary using hash table.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public abstract class AbstractHashTable  extends Monitorable implements Dictionary {
    public final static int DEFAULT_SIZE = 50;

    protected Entry[] table;
    protected int entries;

    /**
     * Create a table with DEFAULT_SIZE. (For use by sub classes.)
     */
    protected AbstractHashTable() { this(DEFAULT_SIZE); }

    /**
     * Create a table with the given default size. (For use by sub classes.)
     */
    protected AbstractHashTable(final int size) {
        this.table = new Entry[size];
        this.entries = 0;
    }

    /**
     * Generate a hash code for the given key using algorithm in Weiss. (For use by sub classes.)
     */
    protected int hashFunction(String key) {
        // Your code here.
        int tablesize = this.table.length;
        int hashval = 0;

        for(int i = 0; i<key.length();i++){
          hashval = 37*hashval+key.charAt(i);
        }

        hashval %= tablesize;

        if(hashval<0){
          hashval+=tablesize;
        }

        return hashval;
    }

    public boolean containsWord(String word) {
        // Your code here.
        for(int i = 0; i<this.table.length;i++){
          if(table[i] != null){
          if(this.table[hashFunction(word)] != null){
            if(this.table[i].isEntryFor(word)){
                return true;
            }
          }}
        }

        return false;
    }

    public List<Definition> getDefinitions(String word) {
        // Your code here.
        assert(word!=null);
        if(containsWord(word)){
          for(int i = 0; i<this.table.length;i++){
            if(this.table[i].isEntryFor(word)){
              return this.table[i].getDefinitions();
            }
          }
        }

        return null;
    }

    public void insert(String word, Definition definition) {
        // Your code here.

        int hash = hashFunction(word);
        table[hash] = new Entry(word);
        table[hash].addDefinition(definition);
        entries+=1;

    }


    public boolean isEmpty() { return entries == 0; }

    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }

    public int size() { return this.entries; }

    /* Hash Table Functions */

    public double loadFactor() { return entries/(double)table.length; }

    /**
     * Method called by <code>insert()</code> when the table needs enlarging.
     * <p>
     * Sub classes should override as required.
     */
    protected void rebuild() {
          throw new IllegalStateException("Hashtable:insert(): table is full.");
    }


    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    protected abstract int findIndex(String word);



    /**
     * Prints contents of table to screen. (Method provided to facilitate testing and debugging.)
     */
    public void dump() {
        Entry[] table = this.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
        System.out.printf("\n#Entries: %d.", this.entries);
    }

    /**
     * Obtain a list of the entries in the dictionary. (Method to facilitate testing and debugging.)
     */
    public java.util.ArrayList<Entry> getWords() {
        java.util.ArrayList<Entry> entries = new java.util.ArrayList<Entry>();
        for (int i=0; i<this.table.length; i++) {
            if (this.table[i]!=null) {
                entries.add(table[i]);
            }
        }
        return entries;
    }

}
