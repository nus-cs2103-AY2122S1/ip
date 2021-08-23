package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * <h1> Event </h1>
 * Encapsulates a task that has a description and a date and time that it occurs on.
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

    /**
     * converts an Event object to a formatted text to be saved in storage.
     *
     * @return text representation of Event in storage files.
     */
    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + dateTime.format(parseFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(outputFormat) + ")";
    }
}