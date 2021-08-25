package duke.exception;

public class DukeDeadlineMissingDateException extends DukeException {
    public DukeDeadlineMissingDateException() {
        super("Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.");
    }
}