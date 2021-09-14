package duke.exception;

/**
 * Represents UnknownCommandException where the input command cannot be parsed.
 *
 * @author Sherman Ng Wei Sheng
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
