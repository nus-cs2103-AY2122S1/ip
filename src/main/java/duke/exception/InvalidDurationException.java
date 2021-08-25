package duke.exception;

public class InvalidDurationException extends DukeException {
    public InvalidDurationException() {
        super("Please define the duration of your event in the following format: " +
                "YYYY/MM/DD HHMM - HHMM in the 24 hours format.");
    }
}
