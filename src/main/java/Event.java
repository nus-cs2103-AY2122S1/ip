import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate at;
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public String getDateString() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.getDateString() + ")";
    }
}