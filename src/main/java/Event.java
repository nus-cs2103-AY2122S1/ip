import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String description, String at) {
        super(description);
        String[] dateTime = at.split(" ");
        this.eventDate =  LocalDate.parse(dateTime[0].trim());
        this.eventTime = LocalTime.parse(dateTime[1].trim());
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTypeIndicator() {
        return "[E]";
    }

    @Override
    public String toFileRecord() {
        return String.format("E | %d | %s | %s",
                this.isDone ? 1 : 0 , this.description, this.at);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " +
                eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy ")) +
                eventTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}