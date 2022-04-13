package duke.task;

import java.util.List;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public ToDo(String name, boolean isDone, List<String> tags) {
        super(name, isDone, tags);
    }

    @Override
    public ToDo markAsDone() {
        return new ToDo(super.getName(), true);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
