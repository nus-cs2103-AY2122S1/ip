package side.exception;

/**
 * TooManyIndexesException is thrown when user attempts to carry out an action requiring index with too many indexes.
 *
 * @author Lua Yi Da
 */

public class TooManyIndexesException extends SideException {

    /**
     * Initialises a TooManyIndexesException.
     */
    public TooManyIndexesException(String input) {
        super("I only have 1 hand, I can only " + input + " 1 at a time...");
    }
}
