public class Deadline extends Task {

    /** Deadline of a task */
    protected String by;

    /**
     * Constructor for Deadline. Takes in a String description and by.
     * Initialises description and deadline of task.
     *
     * @param description The name of the deadline.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
