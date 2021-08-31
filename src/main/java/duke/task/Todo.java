package duke.task;

/**
 * Todo class to represent a class without a deadline.
 */
public class Todo extends Task {
    /**
     * Constructor of Todo class.
     *
     * @param description Description of a task without deadline.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of Todo class.
     *
     * @param description Description of a task without deadline.
     * @param isDone Completion status of task.
     */
    public Todo(String description, Boolean isDone) {
        super(description);
        super.isDone = isDone;
    }

    /**
     * Formats task's data into a string for storage in hard disk
     * and returns it.
     *
     * @return String containing task's data.
     */
    @Override
    public String getData() {
        return "Todo // " + (super.getIsDone() ? 1 : 0) + " // " + super.getDescription();
    }

    /**
     * Overrides Task class's toString method.
     *
     * @return A String describing details of todo class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
