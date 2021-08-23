package duke.task;

import duke.DukeException;

public class InvalidTaskSelectedException extends DukeException {
    public InvalidTaskSelectedException() {
        super("Invalid task selected!");
    }
}
