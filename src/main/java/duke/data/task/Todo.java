package duke.data.task;

import duke.data.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String formatToWrite() {
        return String.format("T | %s", super.formatToWrite());
    }
}
