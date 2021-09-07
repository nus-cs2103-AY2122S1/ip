package duke.task;

/**
 * <h1> Todo </h1>
 * Encapsulates a task that solely has a description.
 *
 * @author Clifford
 */

public class Todo extends Task {
    protected static final String TASK_SYMBOL = "[T]";

    public Todo(String description) {
        super(description, TASK_SYMBOL, null);
    }

    /**
     * Converts a Todo object to a formatted text to be saved in storage.
     *
     * @return text representation of Todo in storage files.
     */
    @Override
    public String convertToText() {
        return super.convertToText();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
