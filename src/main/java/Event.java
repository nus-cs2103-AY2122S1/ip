import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task with scheduled date.
 */
public class Event extends Task {
    /**
     * Scheduled date of the task.
     */
    private LocalDateTime at;
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    /**
     * Instantiates an Event
     *
     * @param description description of the event
     * @param at scheduled date of the event
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the string representation of this Event object.
     *
     * @return String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + "|" + this.at.format(outputFormatter);
    }
}
