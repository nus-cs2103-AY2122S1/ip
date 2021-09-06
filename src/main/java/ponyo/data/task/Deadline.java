package ponyo.data.task;

import ponyo.common.Prefixes;

/**
 * A Deadline task object that has a description and a by-date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a new deadline instance.
     *
     * @param description a string description of the deadline
     * @param by a string by-date for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toStringInFile() {
        return Prefixes.PREFIX_DEADLINE + " - " + super.toStringInFile() + " - " + by;
    }

    @Override
    public String toString() {
        return "[" + Prefixes.PREFIX_DEADLINE + "]" + super.toString() + " (by: " + by + ")";
    }
}
