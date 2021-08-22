package duke.tasks;

import duke.tasks.Task;

public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "| T" + " | " + super.toSaveString();
    }
}