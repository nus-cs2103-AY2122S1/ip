package Exceptions;

public class EmptyTimestampException extends DukeException {

    public EmptyTimestampException() {
        super("The timestamp of a Tasks.Deadline/Tasks.Event task cannot be empty!");
    }
}
