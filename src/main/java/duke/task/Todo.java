package duke.task;

/**
 * A Todo is a Task without a deadline or date.
 */
public class Todo extends Task {
    /**
     * Initialises a Todo with a description.
     *
     * @param description the description String of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
