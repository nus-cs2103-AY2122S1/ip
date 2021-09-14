package duke.exceptions;

public class DukeInvalidFormatException extends DukeException {
    public DukeInvalidFormatException() {
        super("Please enter the date as YYYY-MM-DD!\n");
    }
}
