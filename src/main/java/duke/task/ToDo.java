package duke.task;

import duke.DukeException;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description == "") {
            throw new DukeException("Looks like you forgot to include a description of the todo.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataString(String delimiter) {
        String tag = "T";
        String done = super.isDone ? "1" : "0";
        return String.join(delimiter, tag, done, super.description);
    }
}
