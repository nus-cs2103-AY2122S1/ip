import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that has a description and an end date
 *
 * @author Clifford
 */

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime dateTime;
    final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy, HHmm");
    final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public Deadline(String description, String dateTime) throws DateTimeParseException {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime, parseFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(outputFormat) + ")";
    }
}