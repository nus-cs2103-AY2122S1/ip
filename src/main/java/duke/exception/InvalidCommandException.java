package duke.exception;

/**
 * The InvalidCommandException is thrown when user types in a command that is unrecognisable by Duke.
 *
 * @author Benedict Chua
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String command) {
        super("BAKA! I don't understand this command!\n"
                + String.format("     Command: %s\n", command)
                + "     You might want to check for spelling and potential whitespaces!");
    }
}

