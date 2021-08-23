package duke.task;

import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task that has a description and an end date
 *
 * @author Clifford
 */

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected static final String TASK_SYMBOL = "[D]";
    protected LocalDateTime dateTime;
    protected static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public Deadline(String description, String dateTime) throws DateTimeParseException {
        super(description, TASK_SYMBOL);
        this.dateTime = LocalDateTime.parse(dateTime.trim(), PARSE_FORMAT);
    }

    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + dateTime.format(PARSE_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(OUTPUT_FORMAT) + ")";
    }
}