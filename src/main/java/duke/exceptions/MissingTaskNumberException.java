package duke.exceptions;

public class MissingTaskNumberException extends DukeException {
    public MissingTaskNumberException() { }

    @Override
    public String toString() {
        return "OOPS!!! To delete a task, the task number cannot be empty.";
    }
}
