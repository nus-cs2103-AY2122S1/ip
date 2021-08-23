import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts at a specific time and ends at a specific time.
 *
 * @author felix-ong
 */
public class Event extends Task {
    /** Date, start and end time of the event */
    protected LocalDate dateTime;

    /**
     * Constructor of a Deadline Task.
     *
     * @param description Short description of task.
     */
    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of a Deadline Task.
     *
     * @return The string representation of a Deadline Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
