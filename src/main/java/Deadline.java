import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String toString() {
        return String.format("[D] [%s] %s (by: %s)",
                isDone ? "X" : " ", 
                content, 
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }
}
