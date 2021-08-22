/**
 * Encapsulates a deadline object.
 * Deadline objects are task objects that needs to be done by a specific date/time.
 *
 * @author Dickson
 */
public class Deadline extends Task {
    /**
     * Constructor for Deadline object.
     *
     * @param description
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Gets string representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
