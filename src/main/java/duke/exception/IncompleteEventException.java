package duke.exception;

public class IncompleteEventException extends DukeException{
    private static String LINE = "____________________________________________________________";
    private static String MESSAGE = "OOPS!!! The description or date of an event cannot be empty.";
    public IncompleteEventException() {
        super(LINE + "\n" + MESSAGE + "\n" + LINE);
    }
}
