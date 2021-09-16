package duke.task;

/**
 * Represents the type of Task which has a deadline.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a Deadline object with given description and deadline.
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
     * Converts Deadline object to its String representation.
     *
     * @return String representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
