package dictionary;
import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class LPHashTable extends AbstractHashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { super(); }

    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(int size) { super(size); }

    protected int findIndex(String word) {
		// Implementation of findIndex() required here.

    int hash = hashFunction(word);

    for(int i = 0; i<this.table.length;i++){
      if(this.table[hash] != null){
        if(this.table[hash].isEntryFor(word)){
          return hash;
        }
      }
      hash++;
      if(hash > (this.table.length-1)){
        hash = 0;
      }

    }

    return -1;
	}

  public boolean containsWord(String word) {
    int hash = hashFunction(word);
    if(table[hash] != null){
      for(int i = 0; i<this.table.length;i++){
        if(this.table[hash] == null){return false;}
        if(this.table[hash].isEntryFor(word)){
          return true;
        }
        hash++;
        if(hash > (this.table.length -1)){
          hash = 0;
        }
        this.probe_count++;
      }
    }

    return false;
  }

  public List<Definition> getDefinitions(String word) {
    if(findIndex(word) != -1){
      return this.table[findIndex(word)].getDefinitions();
    }
    else return null;
  }

  public void insert(String word, Definition definition) {
    boolean b = false;

    for(int i = 0; i < this.table.length; i++){
      if(table[i] == null){
          b = true;
      }
    }
    if(b){
      int hash = hashFunction(word);
      if(table[hash] == null){
        super.insert(word,definition);
      }
      else{
        for(int i = 0; i<this.table.length;i++){
          if(table[hash] == null){
            table[hash] = new Entry(word);
            table[hash].addDefinition(definition);
            entries+=1;
            break;

          }
          if(this.table[hash].isEntryFor(word)){
            table[hash].addDefinition(definition);
            break;
          }
          hash++;
          if(hash > (this.table.length - 1)){
            hash = 0;
          }
          this.probe_count++;
        }

      }
    }

  }
  public int getIndex(String word){
    return findIndex(word);
  }

  public int gethash(String word){
    return hashFunction(word);
  }

  public Entry[] gettable(){
    return table;
  }

}
