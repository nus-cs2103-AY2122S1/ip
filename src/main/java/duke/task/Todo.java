package duke.task;

import duke.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    public Todo(String description, boolean isDone) {
        super(description,
                isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    @Override
    public String saveString() {
        return String.format("T|%s|%s",
                super.description,
                super.isDone ? "1" : "0");
    }
}
