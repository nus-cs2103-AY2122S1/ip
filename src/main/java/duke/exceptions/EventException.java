package duke.exceptions;

import duke.exceptions.DukeException1;

public class EventException extends DukeException1 {
    public EventException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a event cannot be empty.";
    }
}
