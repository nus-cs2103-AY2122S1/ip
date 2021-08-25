package ponyo.data.task;

/**
 * A Todo task object that only has a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    // File operations
    @Override
    public String toStringInFile() {
        return "T - " + super.toStringInFile();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
