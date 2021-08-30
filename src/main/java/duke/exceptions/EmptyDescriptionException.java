package duke.exceptions;

/**
 * This is an EmptyDescriptionException that extends DukeException.
 * This exception is thrown when a Command is provided without descriptions.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * This is the class field of EmptyDescriptionException instance.
     */
    private final String command;

    /**
     * This is a EmptyDescriptionException Constructor.
     *
     * @param command A String representing the command provided but without description.
     */
    public EmptyDescriptionException(String command) {
        super("");
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("OOPS!!! The description of %s cannot be empty.", this.command);
    }
}
