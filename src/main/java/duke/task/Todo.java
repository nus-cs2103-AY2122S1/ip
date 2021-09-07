package duke.task;

import duke.task.Task;

public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public String encoding() {
        return "T&&" + super.encoding();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
