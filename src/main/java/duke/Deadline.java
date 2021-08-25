package duke;

/**
 * Represents a task that has a description and deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * A constructor to create a Deadline object.
     *
     * @param description The description of the Deadline object.
     * @param by The deadline of the Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by.equals("") ? "by: " : by;
        super.deadline = this.by;
    }

    /**
     * Returns the string representation of a Deadline object.
     *
     * @return The string representation of a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + by + ")";
    }
}
