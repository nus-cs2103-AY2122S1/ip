import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (this.time == null) {
            return "[E]" + super.toString() + " (at: " + dateString + ")";
        } else {
            String timeString = this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return "[E]" + super.toString() + " (at: " + dateString + " " + timeString + ")";
        }
    }
}