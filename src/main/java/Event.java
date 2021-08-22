import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    private LocalDateTime time;

    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    public String getWhen() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dtf.format(time);
    }

    @Override
    public String displayInfo() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }
}
