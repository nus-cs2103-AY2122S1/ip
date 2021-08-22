package duke;

/**
 * A simple task with no deadline
 */
public class Todo extends Task {
    /**
     * Creates a duke.Todo object that does not have any dates.
     *
     * @param description The description of the task to be done
     */
    public Todo(String description) {
        super(description, "T");
    }
    @Override
    public String toString() {
        return super.toString(); // No preposition
    }
}
