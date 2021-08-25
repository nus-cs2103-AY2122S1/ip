package duke.exception;

import duke.exception.DukeException;

public class DukeEventMissingDateException extends DukeException {
    public DukeEventMissingDateException() {
        super("Invalid use of 'deadline' command!! @_@\n\tTo add a new deadline, use 'deadline <task> /by <due-date>'.");
    }
}