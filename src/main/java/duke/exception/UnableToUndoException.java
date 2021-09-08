package duke.exception;

import duke.command.Command;

public class UnableToUndoException extends DukeException {
    /**
     * Constructor of the class `UnableToUndoException`.
     */
    public UnableToUndoException(Command lastCommand) {
        super(String.format("☹ OOPS!!! I cannot undo the previous command: %s\n", lastCommand.toString()));
    }
}
