import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public Event(String description, String by) {
        super(description);
        this.at = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        String formattedAt = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }
}