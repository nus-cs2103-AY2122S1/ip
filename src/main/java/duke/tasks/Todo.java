package duke.tasks;

import duke.tasks.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + description;
    }

    @Override
    public String getStatusString() { return "D@" + (isDone ? 1 : 0) + "@" + this.description; }
}
