package duke.commands;

import duke.commands.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [T][X] %s", this.description);
    }
}
