package duke.tasks;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for Deadline.
     *
     * @param description of the deadline.
     * @param by          when the deadline is.
     * @param isDone      if the task is done or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}