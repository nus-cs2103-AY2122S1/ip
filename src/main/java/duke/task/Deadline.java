package duke.task;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


/**
 * <h1> Deadline </h1>
 * Encapsulates a task that has a description and a date and time to be completed by.
 *
 * @author Clifford
 */
public class Deadline extends Task {

    protected final static String taskSymbol = "[D]";
    protected LocalDateTime dateTime;
    protected final DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public Deadline(String description, String dateTime) throws DateTimeParseException {
        super(description, taskSymbol);
        this.dateTime = LocalDateTime.parse(dateTime.trim(), parseFormat);
    }

    /**
     * Converts a Deadline object to a formatted text to be saved in storage.
     *
     * @return text representation of Deadline in storage files.
     */
    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + dateTime.format(parseFormat);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(outputFormat) + ")";
    }
}