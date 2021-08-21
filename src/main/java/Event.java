import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that has a description and takes place at a specific time.
 *
 * @author Clifford
 */

public class Event extends Task {
    protected final static String taskSymbol = "[E]";
    protected LocalDateTime dateTime;
    protected final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public Event(String description, String dateTime) throws DateTimeParseException {
        super(description, taskSymbol);
        this.dateTime = LocalDateTime.parse(dateTime.trim(), parseFormat);
    }

    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + dateTime.format(parseFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(outputFormat) + ")";
    }
}