package duke.task;

import duke.exception.DukeException;

/** A class that represents a to-do task. */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public Todo(String description, Priority priority, boolean isDone) throws DukeException {
        super(description, priority, isDone);
    }

    @Override
    public String serialize() {
        return String.join(" | ", "T", this.priority.toString(), this.isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
