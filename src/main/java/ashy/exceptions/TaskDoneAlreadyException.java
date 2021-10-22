package ashy.exceptions;

public class TaskDoneAlreadyException extends AshyException {
    public TaskDoneAlreadyException() {
        super("Oh no! You have completed this task already!");
    }
}
