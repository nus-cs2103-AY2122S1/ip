import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String at;
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String description, String at) {
        super(description);
        setDateTime(at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        setDateTime(at);
    }

    private void setDateTime(String at) {
        this.at = at;
        String[] dateTime = at.split(" ");
        this.eventDate =  LocalDate.parse(dateTime[0].trim());
        this.eventTime = LocalTime.parse(dateTime[1].trim());
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