package duke.exceptions;

public class DeadlineFormatException extends DukeException {
    public DeadlineFormatException() {
        super("☹ OOPS!!! Please use the format: deadline <description> /by yyyy-mm-ddTHH:mm");
    }
}

