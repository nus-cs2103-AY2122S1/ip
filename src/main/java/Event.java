import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class extends Task class and encapsulate an event task
 * that start at a specific time and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +")";
    }
}
