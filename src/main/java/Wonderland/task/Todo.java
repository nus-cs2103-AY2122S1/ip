package Duke.task;

/**
 * The To-do class extends Duke.Duke.task.Task class and encapsulates a to-do Duke.Duke.task
 * with no time attached. 
 */
public class Todo extends Task {
    private final String symbol = "[T]";

    /**
     * Constructor for an to-do Duke.Duke.task.
     *
     * @param description String description of to-do Duke.Duke.task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string for saving to-do Duke.Duke.task to data file.
     *
     * @return string for saving to-do Duke.Duke.task to data file.
     */
    @Override
    public String toFileEntry() {
        return "T" + "/next" + super.isDone + "/next" + super.description;
    }

    /**
     * Returns string representation of to-do object.
     *
     * @return string representation of to-do object.
     */
    @Override
    public String toString() {
        return this.symbol + super.toString();
    }
}
