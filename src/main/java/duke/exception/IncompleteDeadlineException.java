package duke.exception;

public class IncompleteDeadlineException extends DukeException{
    private static String LINE = "____________________________________________________________";
    private static String MESSAGE = "OOPS!!! The description or date of a deadline cannot be empty.";
    public IncompleteDeadlineException() {
        super(LINE + "\n" + MESSAGE + "\n" + LINE);
    }
}

