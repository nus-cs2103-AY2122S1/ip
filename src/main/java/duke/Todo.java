package duke;

/**
 * todo task. Represents a task that is yet to be done with no specific timeframe.
 */
public class Todo extends Task {

    /**
     * Creates new todo task with description
     *
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the todo task into a string.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
