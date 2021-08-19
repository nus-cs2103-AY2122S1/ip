public class Deadline extends Task{

    protected String by;

    /**
     * Constructor to create a Deadline.
     * @param description Description of the Deadline task.
     * @param by Date of when Deadline is due.
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
