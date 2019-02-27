package dictionary;
import java.util.List;

public class QPHashTable extends AbstractHashTable {

  public QPHashTable() { super(); }

  public QPHashTable(int size) { super(size); }

  protected int findIndex(String word) {

    int hash = hashFunction(word);
    if(this.table[hash] != null){
            if(this.table[hash].isEntryFor(word)){
             return hash;
            }
        }
    for(int i = 1; i<=this.table.length;i++){
      int probe = hash + (i*i);
      if(probe < (this.table.length - 1) ){
        if(this.table[probe] != null){
            if(this.table[probe].isEntryFor(word)){
             return probe;
            }
        }
        else{
            probe %=this.table.length;
          //System.out.println(probe % (this.table.length - 1));
          if(this.table[probe] != null){
            if(this.table[probe].isEntryFor(word)){
                return probe;
            }
          }
        }
      }

    }

    return -1;
  }

  public boolean containsWord(String word) {

    int hash = hashFunction(word);
    if(this.table[hash] != null){
            if(this.table[hash].isEntryFor(word)){
             return true;
            }
        }
    for(int i = 1; i<=this.table.length;i++){
      int probe = hash + (i*i);
      if(probe < (this.table.length - 1) ){
        if(this.table[probe] != null){
            if(this.table[probe].isEntryFor(word)){
             return true;
            }
        }
        else{
            probe %=this.table.length;
          //System.out.println(probe % (this.table.length - 1));
          if(this.table[probe] != null){
            if(this.table[probe].isEntryFor(word)){
                return true;
            }
          }
        }
      }
      this.probe_count++;

    }

    return false;
  }

  public void insert(String word, Definition definition) {

    //if( loadFactor() >1){
    //  rebuild();
    //}

    boolean b = false;

    for(int i = 0; i < this.table.length-1; i++){
      if(table[i] == null){
          b = true;
      }
    }
    if(b){
      int hash = hashFunction(word);
      if(table[hash] == null){
        table[hash] = new Entry(word);
        table[hash].addDefinition(definition);
        entries+=1;
      }
      else{
        for(int i = 1; i<=this.table.length-1;i++){
          int probe = hash + (i*i);
          if(probe < (this.table.length-1) ){
            if(table[probe] == null){  //System.out.println(probe+"  st:"+i+"  "+word);
            table[probe] = new Entry(word);
            table[probe].addDefinition(definition);
            entries+=1;
            break;
            }
          }
          else{

          probe %=this.table.length;
            //System.out.println(probe+"  st:"+i+"  "+word);
            if(table[probe] == null){
            table[probe] = new Entry(word);
            table[probe].addDefinition(definition);
            entries+=1;
            break;
            }
          }
          this.probe_count++;
        }
      }
    }
  }

  protected void rebuild(){
    Entry[] tables = new Entry[NextPrime(table.length)];
    for(int i = 1; i<=this.table.length-1;i++){
      tables[i] = table[i] ;
      //tables[i].addDefinition(table[i].getDefinitions()[1]);
    }
    table = tables;
  }

  public void rebuilder(){
    rebuild();
  }

  public boolean IsPrime(int number){
    if (number == 2 || number == 3){
        return true;}
    if (number % 2 == 0 || number % 3 == 0){
        return false;}
    int divisor = 6;
    while (divisor * divisor - 2 * divisor + 1 <= number){
        if (number % (divisor - 1) == 0){
            return false;}
        if (number % (divisor + 1) == 0){
            return false;}
        divisor += 6;
    }
      return true;
  }

  public int NextPrime(int a){
      while (!IsPrime(++a))
      { }
      return a;
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
