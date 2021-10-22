package ashy.exceptions;

public class EmptyTaskListException extends AshyException {
    public EmptyTaskListException() {
        super("Oh no! There are no tasks in your to-do list! ☹");
    }
}
