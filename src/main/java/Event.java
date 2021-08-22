import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime time;

    // for comparing event objects by description
    public Event(String description) {
        super(description);
    }

    public Event(String description, LocalDateTime at) {
        super(description);
        date = at.toLocalDate();
        time = at.toLocalTime();
    }

    public Event(String description, LocalDate at) {
        super(description);
        date = at;
        time = null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " " + (time == null ? "All day" :
                time.format(DateTimeFormatter.ofPattern("h.mma", Locale.ENGLISH))) + ")";
    }
}