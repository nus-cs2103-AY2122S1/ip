import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    protected LocalTime time;
    protected LocalDate date;

    public Event(String description, LocalTime time, LocalDate date) {
        super(description);
        this.time = time;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
                + ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
