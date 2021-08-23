package duke.task;

/**
 * Encapsulates a task that solely has a description
 *
 * @author Clifford
 */

public class Todo extends Task {
    protected static final String TASK_SYMBOL = "[T]";

    public Todo(String description) {
        super(description, TASK_SYMBOL);
    }

    @Override
    public String convertToText() {
        return super.convertToText();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}