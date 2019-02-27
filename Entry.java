package dictionary;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 * Simple datatype for storing a word and its definitions in a hashtable.
 * 
 * @author Stephan Jamieson
 * @version 24/04/2015
 */
public class Entry {

    
    private String word;
    private List<Definition> definitions;
    
    /**
     * Create an Entry for the given word, of the given type, with the given definition.
     */
    public Entry(final String word) {
        assert(word!=null);
        this.word = word;
        this.definitions = new ArrayList<Definition>();
    }

        
    /* **** Implementation of Entry interface. **** */
    /**
     * Add a definition for the word. (There can be more than one.)
     */
    public void addDefinition(final WordType wordType, final String description) {
        this.addDefinition(new Definition(wordType, description));
    }
    public void addDefinition(final Definition definition) { this.definitions.add(definition); }
        
    /**
     * Obtain the definitions of the word.
     */
    public List<Definition> getDefinitions() { return new ArrayList<Definition>(this.definitions); }
    
    
    /**
     * Obtain the word defined in this entry.
     */
    public String getWord() { return this.word; }
    
    
    public boolean isEntryFor(String word) { return this.getWord().equals(word); }
    
    /* **** utility methods **** */
   
    public String toString() {
        StringBuffer result = new StringBuffer(this.getWord());
        result.append(": [");
        
        Iterator<Definition> iterator = this.getDefinitions().iterator();
        if (iterator.hasNext()) {
            result.append(iterator.next());
        }
        while (iterator.hasNext()) {
            result.append("; ");
            result.append(iterator.next());
        }
        result.append("]");
        return result.toString();
    }
    
}
