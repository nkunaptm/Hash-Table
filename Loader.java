package dictionary;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//
import java.util.List;
import java.util.Scanner;
/**
 * Utility for loading a dictionary data from a file.
 * File format (in ad-hoc BNF):
 * <p>
 * &lt;lexicion&gt;::={&lt;entry&gt;}</br>
 * &lt;entry&gt;::=&lt;word type&gt; : &lt;word&gt; : [&lt;description&gt;]</br>
 * &lt;word type&gt;::=a | v | n</br>
 * &lt;word&gt;::={&lt;letter&gt;}</br>
 * &lt;description&gt;::={&lt;character&gt;}
 * <p>
 * The file contains 0 or more entries. An entry consists of word type followed by a colon, 
 * followed by the word, followed by a colon, optionally followed by a description.
 * <p>
 * The word type is represented by a single character: 'a' or 'n' or 'v'. 
 * <p>
 * A word consists of a sequence of one or more letters.
 * <p>
 * A descrption consists of 1 or more characters (generally, it's a word phrase).
 * 
 * @author Stephan Jamieson
 * @version 20/3/2016
 */
public class Loader {

    private Dictionary dictionary;    

    public Loader(final Dictionary target) {
        this.dictionary=target;
    }

    /**
     * Obtain the target dictionary that this Loader operates upon.
     */
    public Dictionary target() { return this.dictionary; }
    
    /**
     * Parse a single entry from the given string and insert it in the target.
     */
    public void load(final String entry) {
        final Scanner scanner = new Scanner(entry).useDelimiter("\\s*:\\s*");
        final WordType wordType = WordType.toWordType(scanner.next());
        final String word = scanner.next();
        String description = "";
        if (scanner.hasNext()) {
            description = scanner.next(); 
        }
        final Definition definition = new Definition(wordType, description);
        dictionary.insert(word, definition);
    }
    
    /**
     * Parse the given file, inserting the results in the target.
     */
    public void load(final File file) throws FileNotFoundException, IOException { 
        assert(dictionary!=null && file!=null);
        BufferedReader reader = new BufferedReader(new FileReader(file));      
        String line = reader.readLine();
        while(line!=null) {
            this.load(line);
            line = reader.readLine();
        }
        reader.close();
    }
    
    /**
     * Parse each String in the given list and insert in the dictionary.
     */
    public void load(final List<String> entries) {
        for (String entry : entries) {
            this.load(entry);
        }
    }
    
}
