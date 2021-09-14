package botto.task;

/**
 * Format for the Botto bot's Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo task.
     *
     * @param description task title.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
