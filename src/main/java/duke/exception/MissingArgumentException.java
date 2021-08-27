package duke.exception;

/**
 * The MissingArgumentException is thrown when the user did not input relevant arguments for their commands.
 *
 * @author Benedict Chua
 */
public class MissingArgumentException extends DukeException {
    public MissingArgumentException(String tag, String type) {
        super(String.format("BAKA! You're missing the %s argument to add a %s!", tag, type));
    }
}

