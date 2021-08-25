import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected final LocalDateTime at;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, formatter);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
