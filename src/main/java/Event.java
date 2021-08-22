import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date;
    private String timeRange;

    public Event(String description, LocalDate date, String timeRange) {
        super(description);
        this.date = date;
        this.timeRange = timeRange;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + date.format(dateFormatter) + " " + this.timeRange + ")";
    }
}
