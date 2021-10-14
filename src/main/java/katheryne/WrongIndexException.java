package katheryne;

/**
 * An exception thrown when the index does not exist within the taskList.
 */
public class WrongIndexException extends KatheryneException {
    public WrongIndexException() {
        super("That's not a valid index...");
    }
}
