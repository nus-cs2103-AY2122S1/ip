package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException(String invalidDateTime) {
        super("Invalid datetime: " + invalidDateTime + "\n"
                + "Please use format: YYYY-MM-DD HH:MM:SS or MMM d YYYY HH:MM:SS\n"
                + "Eg. 2021-08-08 05:05:05 or Aug 8 2021 05:05:05");
    }
}
