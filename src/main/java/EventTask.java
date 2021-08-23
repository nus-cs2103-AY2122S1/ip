import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime time;
    static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM y, E, kk:mm");

    public EventTask(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DATE_FORMAT) + ")";
    }
}