import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public Event(String description, LocalDate time) {
        super(String.format(
            "%s (at: %s)",
            description,
            time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
    ));
    }

    public String getTaskIcon() {
        return "E";
    }
}
