import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String IDENTIFIER = "D";
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private LocalDateTime by;

    public Deadline(String description, boolean isDone, String by) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    public Deadline(String description, String by) throws DateTimeParseException{
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[" + IDENTIFIER + "]" + super.toString() +
            " (by:" + by.format(DISPLAY_FORMAT) + ")";
    }

    @Override
    public String toStorageFormat() {
        return IDENTIFIER + STORAGE_DELIMITER + super.toStorageFormat() + STORAGE_DELIMITER +
            by.format(INPUT_FORMAT);
    }
}
