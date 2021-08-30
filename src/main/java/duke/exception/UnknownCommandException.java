package duke.exception;

/**
 * Thrown when user inputs an unknown / invalid command.
 *
 * @author Zhang Shi Chen
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry but my database does not have such command.");
    }
}
