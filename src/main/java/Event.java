import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    // Time which event happen at
    protected String at;

    public Event(String description, String at) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(at);
            this.at = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.at = at;
        }
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return string representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
