/**
 * Class for deadline tasks.
 */
public class Deadline extends Task{

    private final String by;

    /**
     * Constructor for a deadline task.
     *
     * @param description String describing the task.
     * @param by Deadline string for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.category = TaskType.deadline;
        this.by = by;
    }

    /**
     * String representation of a deadline task.
     *
     * @return String describing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
