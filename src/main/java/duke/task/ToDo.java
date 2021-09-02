package duke.task;

/**
 * Represents a To-do.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toText() {
        String[] props = {"T", super.getStatusIcon(), super.getName()};
        return String.join(" | ", props);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
