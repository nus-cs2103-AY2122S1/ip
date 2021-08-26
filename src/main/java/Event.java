import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-yyyy H:mm");
    private static DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm a");
    protected LocalDateTime date;

    public Event(String description, String at) {
        super(description);
        this.date = parseDateTime(at);
    }
    
    private LocalDateTime parseDateTime(String at) {
        // TODO: need to catch exception
        return LocalDateTime.parse(at, FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DISPLAY_FORMATTER) + ")";
    }
}
