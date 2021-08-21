import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This class represents an Event.
 *  An event: tasks that start at a specific time and ends at a specific time.
 *
 * @author Ryan Tian Jun.
 */
public class Event extends Task {

    private String by;
    private LocalDate date;

    public Event(String description, Task.TYPE type, String by) {
        super(description, type);
        try {
            LocalDate d1 = LocalDate.parse(by);
            this.date = d1;
            this.by = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException dateTimeParseException) {
            this.by = by;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }
}
