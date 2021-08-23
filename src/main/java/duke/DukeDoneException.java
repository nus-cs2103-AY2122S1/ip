package duke;

import duke.DukeException;

public class DukeDoneException extends DukeException {
    @Override
    public String getMessage() {
        return "OOPS!!! Please specify which task.";
    }
}
