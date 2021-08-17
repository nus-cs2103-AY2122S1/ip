/**
 * This class keeps track of tasks with a deadline.
 */
public class Deadline extends Task{

    private String by;

    /**
     * Constructor, to initialize a deadline task.
     *
     * @param description Description of the task.
     * @param by Time to do the task by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline task description and its status in an
     * organised format.
     *
     * @return A String including the deadline task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
