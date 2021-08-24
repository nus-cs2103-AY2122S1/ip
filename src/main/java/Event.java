import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate dt;

    public Event(String description, LocalDate dt) {
        super(description);
        this.dt = dt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
