package duke.task;

/**
 * Type of Task which has a deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts Deadline object into String form to save.
     *
     * @return String form of Deadline object to save.
     */
    @Override
    public String convertToString() {
        return "D|" + super.convertToString() + "|" + by;
    }

    /**
     * String representation of Deadline object.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
