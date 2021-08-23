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
     * Constructor of an Event Task.
     *
     * @param description Short description of task.
     */
    public Event(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor of an Event Task.
     *
     * @param description Short description of task.
     */
    public Event(String description, LocalDate dateTime, boolean isDone) {
        super(description);
        this.dateTime = dateTime;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the task to be saved in.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + String.format(",%s", this.dateTime);
    }

    /**
     * Returns the string representation of an Event Task.
     *
     * @return The string representation of an Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
