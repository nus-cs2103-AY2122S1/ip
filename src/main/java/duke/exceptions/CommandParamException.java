package duke.exceptions;

/**
 * This is a duke.exceptions.CommandParamException that extends duke.exceptions.DukeException.
 */
public class CommandParamException extends DukeException {

    private final String command;
    public CommandParamException(String command) {
        super("");
        this.command = command;
    }

    @Override
    public String getMessage() {
        return String.format("☹ OOPS! I don't think your %s is keyed in correctly.☹", this.command);
    }
}
