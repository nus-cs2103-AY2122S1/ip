package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String convertToString() {
        return "T|" + super.convertToString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
