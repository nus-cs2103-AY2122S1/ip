package side.exception;

/**
 * NoIndexException is thrown when user attempts to carry out an action requiring index without a index.
 *
 * @author Lua Yi Da
 */

public class NoIndexException extends SideException {

    /**
     * Initialises a NoIndexException.
     */
    public NoIndexException() {
        super("Can't do anything without more info...");
    }
}
