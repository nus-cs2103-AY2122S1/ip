import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Tasks {
    protected static final DateTimeFormatter formatted = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
    protected LocalDateTime startTime;

    public Event(String description, LocalDateTime startTime) {
        super(description.substring(6, description.indexOf("/at")));
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime.format(formatted) + ")";
    }
}