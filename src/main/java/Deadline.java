/**
 * Represents a Deadline Task which inherits from Task and contains the dateTime for the deadline.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Deadline extends Task {
    private final String dateTime;
    
    /**
     * Constructor to initialise a new Deadline.
     *
     * @param description The description of the task.
     * @param dateTime The date and time of the deadline for the task.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Overridden toString method.
     *
     * @return The String representation of the task, prefixed with a status icon and deadlines identifier.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.dateTime);
    }
}
