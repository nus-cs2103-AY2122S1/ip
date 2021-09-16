package duke.task;

/**
 * Todo class which represents a task without a deadline.
 */
public class Todo extends Task {
    /**
     * Constructor of Todo class.
     *
     * @param description Description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of Todo class.
     *
     * @param description Description of todo task.
     * @param isDone Completion status of task.
     */
    public Todo(String description, Boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    /**
     * Formats task's data into a string for storage in duke.txt.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Todo // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription();
    }

    /**
     * Returns a string describing details of the todo task.
     *
     * @return A String describing details of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
