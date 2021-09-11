package duke.exception;

/**
 * Class that encapsulates an invalid date/time Duke-specific exception.
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Constructs a InvalidDateTimeException instance.
     */
    public InvalidDateTimeException() {
        super("Incorrect date/time format given!");
    }
}
