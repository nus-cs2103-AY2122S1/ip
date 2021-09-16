package duke.exception;

/**
 * Exception for invalid command in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeInvalidCommandException extends DukeException {
    /**
     * Constructor for DukeInvalidCommandException.
     */
    public DukeInvalidCommandException() {
        super("Invalid command @_@ Try typing 'help' to see my list of commands!");
    }
}
