package duke;

/**
 * Represents the Deadline type of Task.
 * Deadlines refer to a type of task that has a string as a description and a deadline to be met.
 */
public class Deadline extends duke.Task {

    private String by;

    /**
     * The constructor of the Deadline.
     *
     * @param description description of Deadline.
     * @param by deadline of Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline in string form.
     * @return Deadline in string form.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}