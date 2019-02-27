package dictionary;
/**
 * A Definition repesents a word definition comprising word type (noun, verb, adjective)
 * and description.
 * 
 * @author Stephan Jamieson 
 * @version 18/3/2016
 */
public class Definition {
    private WordType wordType;
    private String description;
    
    /**
     * Create a Definition with the given wordType and description.
     */
    public Definition(WordType wordType, String description) {
        this.wordType = wordType;
        this.description = description;
    }
    
    /**
     * Obtain the WordType.
     */
    public WordType getType() { return wordType; }
    
    /**
     * Obtain the description.
     */
    public String getDescription() { return description; }
    
    /**
     * Obtain a String representation in the form "(<word type>) <description>".
     */
    public String toString() { return "("+wordType+") "+description; }
}
