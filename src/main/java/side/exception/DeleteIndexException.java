package side.exception;

/**
 * DeleteIndexException is thrown when user attempts to delete a task without a valid index.
 *
 * @author Lua Yi Da
 */

public class DeleteIndexException extends SideException {

    /**
     * Initialises a DeleteIndexException.
     */
    public DeleteIndexException() {
        super("Can't delete what isn't there...");
    }
}
