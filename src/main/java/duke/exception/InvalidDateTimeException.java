package duke.exception;

/**
 * Exception thrown when an invalid date time is found.
 */
public class InvalidDateTimeException extends DukeException {
    /**
     * Creates a new exception when an invalid date time is found.
     * @param invalidDateTime Invalid date time.
     */
    public InvalidDateTimeException(String invalidDateTime) {
        super("Invalid datetime: " + invalidDateTime + "\n"
                + "Please use format: YYYY-MM-DD HH:MM:SS or MMM d YYYY HH:MM:SS\n"
                + "Eg. 2021-08-08 05:05:05 or Aug 8 2021 05:05:05");
    }
}
