import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements DateTimeable {

    public final String code = "D";
    private final LocalDateTime by;

    public Deadline(String description, String by) throws DukeException, DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[" + code + "]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.by;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}