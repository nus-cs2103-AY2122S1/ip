package duke;

/**
 * Represents a task that has a description and deadline.
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * A constructor to create a Deadline object.
     *
     * @param description The description of the Deadline object.
     * @param deadline The deadline of the Deadline object.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline.equals("") ? "by: " : deadline;
        super.deadline = this.deadline;
    }

    /**
     * Returns the string representation of a Deadline object.
     *
     * @return The string representation of a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + deadline + ")";
    }
}
