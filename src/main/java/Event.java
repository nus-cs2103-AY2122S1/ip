import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a description and a start and end date/time
 */
public class Event extends Task {

    private LocalDate eventDate;

    Event(String description, boolean completed, LocalDate eventDate) {
        super(description, completed);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String encode() {
        return "e" + DukeMemory.DELIMITER
                + (this.isCompleted() ? "1" : "0") + DukeMemory.DELIMITER
                + eventDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + DukeMemory.DELIMITER
                + this.getDescription();
    }
}
