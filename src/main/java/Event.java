import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String taskType = "E";
    private LocalDateTime date;
    public Event(String event, LocalDateTime date) {
        super(event);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)",
                this.taskType, super.toString(),dateToString(this.date));
    }
}
