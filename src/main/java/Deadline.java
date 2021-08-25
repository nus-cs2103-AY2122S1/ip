import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task which inherits from Task and contains the dateTime for the deadline.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Deadline extends Task {
    private final LocalDate dateTime;
    
    /**
     * Constructor to initialize a new Deadline.
     *
     * @param description The description of the task.
     * @param dateTime The date and time of the deadline for the task.
     */
    public Deadline(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the task in a desired format.
     *
     * @return The String representation of the task, prefixed with a status icon and deadlines identifier.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", 
                this.getStatusIcon(), 
                this.description, 
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
