public class Deadline extends Task {
    protected String by;

    /**
     * A constructor to create a Deadline object
     *
     * @param description The description of the Deadline object
     * @param by The deadline of the Deadline object
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * A method to get the string representation of a Deadline object
     *
     * @return The string representation of a Deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
