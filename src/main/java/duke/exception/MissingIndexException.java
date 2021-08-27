package duke.exception;

/**
 * The MissingIndexException is thrown when the user did not input the index for certain commands.
 *
 * @author Benedict Chua
 */
public class MissingIndexException extends DukeException {
    public MissingIndexException() {
        super("BAKA! You need to provide an index so I know which task you are referring to!");
    }
}

