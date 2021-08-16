package duke.task;

/**
 * Encapsulates task Deadline.
 *
 * @author limzk126
 * @version Level-6
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of deadlined task.
     * @param by Date/time of task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")\n";
    }
}
