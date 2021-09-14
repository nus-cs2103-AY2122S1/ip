package duke.exception;

public class InvalidDateTimeFormatException extends DukeException {

    private static final String INVALID_DATE_TIME_MSG = "Please use the specified date format (YYYY-MM-DD)!";

    public InvalidDateTimeFormatException() {
        super(INVALID_DATE_TIME_MSG);
    }
}
