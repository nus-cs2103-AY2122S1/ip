package duke.exception;

/**
 * InvalidTimeException class to handle invalid time formats.
 */
public class InvalidTimeException extends DukeException {

    /**
     * Constructor for InvalidNoDateException class.
     */
    public InvalidTimeException() {
        super("Sorry >.< but this time format is invalid!"
                + "\n"
                + "Please follow this format: "
                + "[hh:mm].");
    }
}
