import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

    private LocalDateTime startTime;

    public Event(String description, LocalDateTime startTime) {
        super(description);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", startTime.format(DT_FORMAT));
    }
}
