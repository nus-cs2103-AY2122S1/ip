import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime at;

    public Event(String content, String at) {
        super(content);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd H:m"));
    }

    @Override
    public String toString() {
        String dateFormat = at.format(DateTimeFormatter.ofPattern("EEE d MMM yyyy, K:ma"));
        return "[E]" + super.toString() + " (at: " + dateFormat + ")";
    }
}