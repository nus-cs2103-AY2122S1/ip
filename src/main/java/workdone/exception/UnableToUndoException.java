package workdone.exception;

import workdone.command.Command;

/**
 * Represents an exception thrown when there are no more commands to undo. A subclass of WorkDoneException.
 */
public class UnableToUndoException extends WorkDoneException {
    /**
     * Constructor of the class `UnableToUndoException`.
     */
    public UnableToUndoException(Command command) {
        super(String.format("☹ OOPS!!! I cannot undo this command: %s", command.toString()));
    }

    public UnableToUndoException() {
        super("☹ OOPS!!! No more previous commands in the stack.");
    }
}
