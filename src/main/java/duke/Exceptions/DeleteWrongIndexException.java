package duke.Exceptions;

/**
 * Represents DeleteWrongIndexException class
 */
public class DeleteWrongIndexException extends Exception {
    /**
     * The Constructor for DeleteWrongIndexException
     * @param message
     */
    public DeleteWrongIndexException(String message) {
        super(String.format("☹ OOPS!!! The target you delete is out of list, please enter again"));
    }
}
