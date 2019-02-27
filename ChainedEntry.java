package dictionary;
/**
 * A hash table entry that supports chaining.
 *
 * @author Stephan Jamieson
 * @version 18/3/16
 */
public class ChainedEntry extends Entry {

    private ChainedEntry next;

    /**
     * Create an entry containing the given word and chained to the given entry.
     * </br>(i.e. the following holds: <code>this.getNext()==next</code>.)
     */
    public ChainedEntry(final String word, final ChainedEntry next) {
        super(word);
        this.next = next;
    }

    /**
     * Convenience constructor that performs <code>this(word, null)</code>.
     */
    public ChainedEntry(final String word) { this(word, null); }

    /**
     * Obtain the next entry in the chain.
     */
    public ChainedEntry getNext() { return next; }
}
