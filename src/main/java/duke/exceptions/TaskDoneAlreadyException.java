package duke.exceptions;

public class TaskDoneAlreadyException extends DukeException {
    public TaskDoneAlreadyException() {
        super("You have completed this task already!");
    }
}
