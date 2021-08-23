import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String time;
    protected String timeToDisplay;
    protected LocalDateTime eventTime;

    public Event(String description, String time) {
        super(description);
        this.time = time;
        int date = Integer.parseInt(time.substring(0, 2));
        int month = Integer.parseInt(time.substring(3, 5));
        int year = Integer.parseInt(time.substring(6, 10));
        int hour = Integer.parseInt(time.substring(11, 13));
        int min = Integer.parseInt(time.substring(14, 16));

        LocalDateTime taskDate = LocalDateTime.of(year, month, date, hour, min);
        this.timeToDisplay = taskDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        eventTime = taskDate;
    }

    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeToDisplay + ")";
    }

    @Override
    public String toPrintToFile() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
