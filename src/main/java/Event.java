import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task implements DateTimeable{

    public final String code = "E";
    private final LocalDateTime at;

    public Event(String description, String at) throws DukeException, DateTimeParseException {
        super(description);
        this.at = LocalDateTime.parse(at);
    }

    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.at;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}