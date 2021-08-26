package duke.exceptions;

import duke.exceptions.DukeException1;

public class TodoException extends DukeException1 {
    public TodoException() {
        super();
    }

    @Override
    public String getMessage() {
        return "\tOOPS!!! The description of a todo cannot be empty.";
    }
}
