import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime details;

    public EventTask(String description, String details) {
        super(description);
        this.details = LocalDateTime.parse(details, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + details.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"))
                + ")";
    }
}