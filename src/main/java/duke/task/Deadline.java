package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the deadline task.
     *
     * @return The deadline.
     */
    public String getBy() {
        assert by != null : "by variable should not be null";
        return by;
    }

    /**
     * Returns the formatted string representation of the task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        assert by != null : "by variable should not be null";
        return "[D]" + super.toString() + " (by: " + by + ")";

    }

}

