import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event, which is a subtype of a Task.
 */
public class Event extends Task {
    protected LocalDate date = null;
    protected LocalTime time = null;

    /**
     * A constructor of an Event.
     *
     * @param description Description of the Event.
     * @param at The date and/or time of the Event.
     */
    public Event(String description, String at) {
        super(description);
        if (at.contains(" ")) {
            this.date = LocalDate.parse(at.split(" ", 2)[0]);
            this.time = LocalTime.parse(at.split(" ", 2)[1]);
        } else if (at.contains("-")) {
            this.date = LocalDate.parse(at);
        } else {
            this.time = LocalTime.parse(at);
        }
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return [E], the description and the at.
     */
    @Override
    public String toString() {
        String toPrint = "[E]" + super.toString() + " (at: ";
        if (date != null) {
            toPrint += date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
        if (time != null) {
            toPrint += " " + time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return toPrint + ")";
    }
}
