package duke.exception;

public class EmptyTaskListDukeException extends DukeException{
    private static final String ERROR_MESSAGE = "It seems that your task list is empty.\n"
            + "Try adding some task using \"todo\", \"deadline\" or \"event\"";
    public EmptyTaskListDukeException() {
        super(ERROR_MESSAGE);
    }
}
