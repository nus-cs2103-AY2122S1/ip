public class Deadline extends Task {
    private String by;

    /**
     * Constructor of the Deadline class
     *
     * @param description description of this deadline
     * @param by the due date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of this deadline
     *
     * @return string representation of this deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.by + ")";
    }
}