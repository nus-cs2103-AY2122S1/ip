package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Your date or time format is wrong! " +
                "Please use the format YYYY/MM/DD HHMM where the time is in 24 hours.");
    }
}
