import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an Event task.
 *
 * @author Kleon Ang
 */
public class Event extends Task {
    private final LocalDateTime at;

    /**
     * Constructor for an Event task.
     *
     * @param description A string describing the Event task.
     * @param at A LocalDateTime indicating when the event is held at.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string including the Event's icon, description and time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[E]" + super.toString() + " (at: " + this.at.format(formatter) + ")";
    }
}