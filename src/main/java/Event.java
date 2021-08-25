import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task which inherits from Task and contains the dateTime for the event.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Event extends Task {
    private final LocalDate dateTime;
    
    /**
     * Constructor to initialize a new Event.
     *
     * @param description The description of the task.
     * @param dateTime The date and time of the event.
     */
    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor to initialize a new Event.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Event(String description, LocalDate dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the task in a desired format.
     *
     * @return The String representation of the task, prefixed with a status icon and events identifier.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                this.getStatusIcon(),
                this.getDescription(),
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
    
    @Override
    public String encodeTaskForStorage() {
        int encodedIsDone = this.getIsDone() ? 1 : 0;
        return String.format("E | %d | %s | %s", encodedIsDone, this.getDescription(), this.dateTime);
    }
}
