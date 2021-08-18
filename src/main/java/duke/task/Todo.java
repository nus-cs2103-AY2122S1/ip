package duke.task;

/**
 * Represents a duke.tasks.Todo object.
 */
public class Todo extends Task {
    /**
     * duke.tasks.Todo constructor.
     *
     * @param description the description
     */
    private Todo(String description) {
        super(description);
    }

    /**
     * Factory duke.tasks.Todo method.
     *
     * @param description the description
     * @return a new duke.tasks.Todo object
     */
    public static Todo of(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", isDone ? "[X]" : "[ ]", description);
    }
}
