package duke.exception;

public class TaskParseException extends DukeException {
    private static final String EXCEPTION_MESSAGE_FORMAT = "Cannot parse Task from \n\t'%s'";

    public TaskParseException(String text) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, text));
    }
}
