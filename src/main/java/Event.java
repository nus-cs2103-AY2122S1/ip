import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends DateTimeTask {

    public final String code = "E";
    private final LocalDateTime at;

    public Event(String[] input) throws DukeException, DateTimeParseException {
        super(input[0]);
        if (input.length != 2) {
            throw new DukeException(DukeException.Type.EVENT);
        }
        this.at = LocalDateTime.parse(input[1]);
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