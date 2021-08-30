package duke.tasks;

/**
 * Reflects a particular Deadline Task
 */
public class Deadline extends Task {

    /** Indicates the date the task is due */
    protected String by;

    /**
     * Constructor to create a deadline task containing a description, and due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Internal constructor called to indicate that the deadline task is completed.
     */
    private Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Outputs the date of the deadline.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Indicates that the deadline task is completed.
     */
    public Deadline markAsDone() {
        return new Deadline(this.description, this.by, true);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
