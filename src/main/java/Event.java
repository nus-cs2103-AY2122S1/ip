import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")";
    }
}
