public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toStringForFile() {
        return "D - " + super.toStringForFile() + " - " + by;
    }


    /**
     * @return the String representation of a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}