package workdone.exception;

/**
 * Represents an exception thrown when there are no more commands to undo. A subclass of WorkDoneException.
 */
public class UnableToUndoException extends WorkDoneException {
    /**
     * Constructor of the class `UnableToUndoException`.
     */
    public UnableToUndoException() {
        super("☹ OOPS!!! No more previous commands in the stack.");
    }
}
