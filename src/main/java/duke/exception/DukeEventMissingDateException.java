package duke.exception;

import duke.exception.DukeException;

public class DukeEventMissingDateException extends DukeException {
    public DukeEventMissingDateException() {
        super("Invalid use of 'event' command!! @_@\n\tTo add a new deadline, use 'event <task> /by <event-time>'.");
    }
}