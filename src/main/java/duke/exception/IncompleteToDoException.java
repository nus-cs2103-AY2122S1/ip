package duke.exception;

public class IncompleteToDoException extends DukeException{
    private static String LINE = "____________________________________________________________";
    private static String MESSAGE = "OOPS!!! The description of a todo cannot be empty.";
    public IncompleteToDoException() {
        super(LINE + "\n" + MESSAGE + "\n" + LINE);
    }
}
