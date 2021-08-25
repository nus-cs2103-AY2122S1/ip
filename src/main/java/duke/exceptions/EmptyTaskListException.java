package duke.exceptions;

public class EmptyTaskListException extends DukeException {
    public EmptyTaskListException() {
        super("There are no tasks in your to-do list! ☹");
    }
}
