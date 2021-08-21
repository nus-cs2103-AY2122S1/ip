import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that has a description and takes place at a specific time.
 *
 * @author Clifford
 */

public class Event extends Task {
    protected LocalDateTime dateTime;
    final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy, HHmm");
    final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public Event(String description, String dateTime) throws DateTimeParseException {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime, parseFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(outputFormat) + ")";
    }
}