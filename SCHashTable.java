package dictionary;
import java.util.List;

public class SCHashTable extends AbstractHashTable {

  protected ChainedEntry[] table;

  public SCHashTable() {
    this.table = new ChainedEntry[DEFAULT_SIZE];
    super.table = this.table;
    entries = 0;
  }

  public SCHashTable(int size) {
    this.table = new ChainedEntry[size];
    super.table = this.table;
    entries = 0;
  }

  public boolean containsWord(String word) {
    int hash = hashFunction(word);
    if(table[hash] != null){
      ChainedEntry hold = new ChainedEntry(table[hash].getWord(),table[hash].getNext());
      if(hold != null){
        while(hold != null){
          if(hold.isEntryFor(word)){
            return true ;
          }
          hold = hold.getNext();
          this.probe_count++;
        }
      }
    }
    return false;
  }

  public List<Definition> getDefinitions(String word) {
    int hash = hashFunction(word);
    if(this.table[hash] != null){
      while(table[hash] != null){
        if(this.table[hash].isEntryFor(word)){
          return this.table[hash].getDefinitions() ;
        }
        table[hash] = table[hash].getNext();

      }
    }
    return null;
  }

  public void insert(String word, Definition definition) {

    int hash = hashFunction(word);
    if(findIndex(word)!=-1){
      ChainedEntry hold = this.table[hash];
      while(hold != null){
        if(hold.getWord().equals(word)){
          hold.addDefinition(definition);
          return;}
        else{
          hold = hold.getNext();
        }
        this.probe_count++;
      }
    }
      if(table[hash]!=null){
        table[hash] = new ChainedEntry(word,table[hash]);
        table[hash].addDefinition(definition);
        entries++;
      }
      else{
        table[hash] = new ChainedEntry(word);
        table[hash].addDefinition(definition);
        entries++;
      }

  }

  protected int findIndex(String word) {
    int hash = hashFunction(word);
    if(table[hash] != null){
      ChainedEntry hold = new ChainedEntry(table[hash].getWord(),table[hash].getNext());
      if(hold != null){
        while(hold != null){
          if(hold.isEntryFor(word)){
            return hash;
          }
          hold = hold.getNext();

        }
      }
    }
    return -1;
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
