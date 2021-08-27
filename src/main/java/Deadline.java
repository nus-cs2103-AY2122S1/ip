import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-M-uuuu H:mm");
    private static DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("d MMM uuuu hh:mm a");
    protected LocalDateTime date;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.date = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String at) {
        // TODO: need to catch exception
        return LocalDateTime.parse(at, FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DISPLAY_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + date.format(FORMATTER);
    }
}