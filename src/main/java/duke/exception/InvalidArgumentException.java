package duke.exception;

/**
 * The InvalidArgumentException is thrown when user types in a argument that is unrecognisable by Duke.
 *
 * @author Benedict Chua
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Constructor for InvalidArgumentException.
     *
     * @param command String containing the incorrect argument that the user has inputted.
     */
    public InvalidArgumentException(String command) {
        super("BAKA! I don't understand this argument in your command!\n"
                + String.format("Argument: %s\n", command)
                + "You might want to check for spelling and potential whitespaces!");
    }
}

