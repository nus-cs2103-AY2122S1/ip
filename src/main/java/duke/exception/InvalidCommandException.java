package duke.exception;

/**
 * Exception due to user inputting an invalid command.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Returns a message to inform user about the command that is invalid.
     *
     * @return A message to inform user about the command that is invalid.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
