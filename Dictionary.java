package dictionary;
import java.util.List;
/**
 * Simple customised map interaface.
 * <p>
 * A dictionary contains words and their definitions. For any given word there may be more than one definition.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public interface Dictionary {
    /**
     * Returns true if this dictionary contains a definition for the specified word.
     */
    boolean containsWord(String word);
    
    /**
     * Return the entry for the specified word, or null if this 
     * dictionary contains no entry for the word.
     */
    List<Definition> getDefinitions(String word);
    
    /**
     * Returns true if this dictionary contains no words.
     */
    boolean isEmpty();
    
    /**
     * Removes all words from the dictionary.
     */
    void empty();
    
    /**
     * Inserts the given word and definition.
     * 
     */
    void insert(String word, Definition definition);
    
    /**
     * Returns the number of words in this dictionary.
     */
    int size();
}
    
