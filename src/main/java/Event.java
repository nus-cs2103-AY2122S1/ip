import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    public static final String IDENTIFIER = "E";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private LocalDateTime at;

    public Event(String description, boolean isDone, String at) throws DateTimeParseException {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, INPUT_FORMAT);
    }

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.at = LocalDateTime.parse(at, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString() +
            " (at:" + at.format(DISPLAY_FORMAT)  + ")";
    }
    @Override
    public String toStorageFormat() {
        return IDENTIFIER + STORAGE_DELIMITER + super.toStorageFormat() + STORAGE_DELIMITER +
            at.format(INPUT_FORMAT);
    }
}
