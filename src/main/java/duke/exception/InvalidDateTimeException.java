package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super("Please enter a date of format dd MMM yyyy (e.g 02 Jun 2021)!");
    }
}