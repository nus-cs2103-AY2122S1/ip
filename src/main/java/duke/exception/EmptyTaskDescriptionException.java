package duke.exception;

public class EmptyTaskDescriptionException extends DukeException {

    public EmptyTaskDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty!");
    }
}
