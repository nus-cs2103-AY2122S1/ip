package ashy.exceptions;

public class TaskDoneAlreadyException extends AshyException {
    public TaskDoneAlreadyException() {
        super("You have completed this task already!");
    }
}
