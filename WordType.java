package dictionary;
/**
 * Enumeration class WordType
 * 
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public enum WordType
{
    NOUN, VERB, ADJECTIVE;
    
    /**
     * Obtain the WordType value for the given string.
     * <p>
     * e.g. <code>WordType.toWordType("noun")</code> produces <code>WordType.NOUN</code>.
     */  
    public static WordType toWordType(String string) {
        string=string.toLowerCase().trim();
        if (string.equals("n")||string.equals("noun")) {
            return NOUN;
        }
        else if (string.equals("a")||string.equals("adjective")) {
            return ADJECTIVE;
        }
        else if (string.equals("v")||string.equals("verb")) {
            return VERB;
        }
        else {
            throw new IllegalArgumentException("WordType.toWordType("+string+"): argument not recognised.");
        }
    }
            
    /**
     * Obtain a String representation of this WordType value.
     */
    public String toString() { return super.toString().toLowerCase(); }
}
