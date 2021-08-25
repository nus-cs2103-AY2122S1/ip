import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime time;

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
