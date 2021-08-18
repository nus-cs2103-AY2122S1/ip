package tasks;

import tasks.Task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
