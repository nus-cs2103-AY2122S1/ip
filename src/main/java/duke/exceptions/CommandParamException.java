package duke.exceptions;

/**
 * This is a CommandParamException that extends DukeException.
 * This exception is thrown when unfitting parameters to a command is provided.
 */
public class CommandParamException extends DukeException {

    /**
     * This is the class field of a CommandParamException instance.
     */
    private final String command;

    /**
     * This is a CommandParamException Constructor.
     * @param command
     */
    public CommandParamException(String command) {
        super("");
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("I don't think your %s is keyed in correctly. Think again!", this.command);
    }
}
