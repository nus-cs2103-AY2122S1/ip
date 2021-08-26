package duke.exceptions;

import duke.exceptions.DukeException1;

public class DeadlineException extends DukeException1 {
    public DeadlineException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a deadline cannot be empty.";
    }
}
