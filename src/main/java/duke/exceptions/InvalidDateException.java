package duke.exceptions;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("The date is not in the format YYYY-MM-DD!");
    }
}
