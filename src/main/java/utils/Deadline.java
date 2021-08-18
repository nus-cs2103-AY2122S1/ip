package utils;

/**
 * The Deadline class encapsulates a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}