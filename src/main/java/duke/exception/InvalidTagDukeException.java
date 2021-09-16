package duke.exception;

/**
 * InvalidTagDukeException class.
 * Used to represent an invalid tag command.
 */
public class InvalidTagDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "OOPS!!! Tag parameter is missing.\n"
            + "eg. tag 2 Important";


    /**
     * InvalidTagDukeException constructor.
     */
    public InvalidTagDukeException() {
        super(ERROR_MESSAGE);
    }
}
