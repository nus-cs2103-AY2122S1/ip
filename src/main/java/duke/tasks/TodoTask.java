package duke.tasks;

/**
 * The {@code TodoTask} class extends from {@code Task}.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }
    public TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
