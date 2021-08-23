import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private static final DateTimeFormatter DT_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
    private static final DateTimeFormatter DT_DATA_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mma");

    private LocalDateTime startTime;

    public Event(boolean isDone, String description, LocalDateTime startTime) {
        super(isDone, description);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", startTime.format(DT_OUTPUT_FORMAT));
    }

    @Override
    public String getData() {
        return String.format("E | %s | %s", super.getData(), startTime.format(DT_DATA_FORMAT));
    }
}
