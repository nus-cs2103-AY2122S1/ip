package duke.exception;

/**
 * Throws exception when input is not a command.
 */
public class IllegalCommandException extends DukeException {

    public IllegalCommandException(String commandStr) {
        super("Meow? There is no command " + commandStr + "!");
    }

}
