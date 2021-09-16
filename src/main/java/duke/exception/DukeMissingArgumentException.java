package duke.exception;

/**
 * Exception for missing arguments in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeMissingArgumentException extends DukeException {
    /**
     * Constructor for DukeMissingArgumentException.
     *
     * @param type Type of missing argument.
     */
    public DukeMissingArgumentException(String type) {
        super(String.format("Error: '%s' argument is missing!", type));
    }
}
