public class Deadline extends Task {
    // End date of the Deadline object
    private final String endDate;

    /**
     * Constructor of a Deadline object.
     *
     * @param title title of the Deadline task
     * @param endDate endDate of the Deadline task
     */
    public Deadline(String title, String endDate) {
        super(title);
        this.endDate = endDate.trim();
    }

    /**
     * Return a String representation of a Deadline task.
     * Starts "[D]" to indicate that it is a Deadline task.
     *
     * @return String representation of an Deadline.
     */
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endDate);
    }
}
