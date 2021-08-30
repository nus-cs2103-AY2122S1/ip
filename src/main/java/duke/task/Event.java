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
    protected static final String taskSymbol = "[E]";
    protected static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    protected LocalDateTime dateTime;


    /**
     * Initialises the Event Task.
     *
     * @param description
     * @param dateTime
     * @throws DateTimeParseException
     */
    public Event(String description, String dateTime) throws DateTimeParseException {
        super(description, taskSymbol);
        this.dateTime = LocalDateTime.parse(dateTime.trim(), PARSE_FORMAT);
    }

    /**
     * Converts an Event object to a formatted text to be saved in storage.
     *
     * @return text representation of Event in storage files.
     */
    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + dateTime.format(PARSE_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime.format(OUTPUT_FORMAT) + ")";
    }
}
