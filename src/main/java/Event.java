import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private String dateTime;
    private final LocalDateTime dt;

    public Event(String description) {
        super(description.substring(0, description.indexOf("/at ")));
        this.dateTime = description.substring(description.indexOf("/at ") + 4);
        this.dt = LocalDateTime.parse(this.dateTime);
    }

    @Override
    public String getTaskInfo() {
        return "E" + "|" + super.getZeroOrOne() + "|" + description + "|" + dateTime;
    }

    @Override
    public String toString() {
        String formatDate = dt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formatTime = dt.format(DateTimeFormatter.ofPattern("hh:mm"));
        return "[E]" + super.toString() + "(at: " + formatDate +  ", " + formatTime + ")";
    }
}