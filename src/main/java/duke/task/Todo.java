package duke.task;

import duke.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String fileFormat() {
        return String.format("T%s", super.fileFormat());
    }

}
