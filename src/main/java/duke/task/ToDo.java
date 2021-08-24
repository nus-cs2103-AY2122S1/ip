package duke.task;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String detail) {
        super(detail, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
