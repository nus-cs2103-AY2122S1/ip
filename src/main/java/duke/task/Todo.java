package duke.task;

/**
 * Encapsulates a task that solely has a description
 *
 * @author Clifford
 */

public class Todo extends Task {
    protected final static String taskSymbol = "[T]";

    public Todo(String description) {
        super(description, taskSymbol);
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