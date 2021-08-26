package duke.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private final LocalDateTime details;

    public EventTask(String description, String details) {
        super(description);
        this.details = LocalDateTime.parse(details, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public EventTask(String isCompleted, String description, String details) {
        super(isCompleted, description);
        this.details = LocalDateTime.parse(details, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public String getDetails() {
        return this.details.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String writeToFile() {
        return String.format("EVENT | %s | %s | %s\n", getIsCompleted(), getDescription(), getDetails());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + details.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"))
                + ")";
    }
}