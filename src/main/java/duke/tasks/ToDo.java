package duke.tasks;

/**
 * Represents a Task that has to be done.
 *
 * @author ruiquan
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String format() {
        return String.format("T, %d, %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
