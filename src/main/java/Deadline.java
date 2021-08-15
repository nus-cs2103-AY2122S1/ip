public class Deadline extends Task {

    protected String endDate;

    /**
     * The constructor for the Deadline class
     * @param description The description of the object
     * @param endDate The endDate
     */
    public Deadline(String description, String endDate) {
        super(description.trim() + " ");
        this.endDate = endDate.trim();
    }

    /**
     * The overridden toString method for the Deadline class
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endDate + ")";
    }
}
