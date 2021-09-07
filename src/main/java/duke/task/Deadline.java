package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * <h1> Deadline </h1>
 * Encapsulates a task that has a description and a date and time to be completed by.
 *
 * @author Clifford
 */
public class Deadline extends Task {
    protected static final String TASK_SYMBOL = "[D]";

    /**
     * Initialises a deadline tasks with the description of the task and expected
     * date of completion.
     *
     * @param description the task description
     * @param dateTime expected date of completion of the task
     * @throws DateTimeParseException
     */
    public Deadline(String description, String dateTime) throws DateTimeParseException {
        super(description, TASK_SYMBOL, LocalDateTime.parse(dateTime.trim(), PARSE_FORMAT));
    }

    /**
     * Converts a Deadline object to a formatted text to be saved in storage.
     *
     * @return text representation of Deadline in storage files.
     */
    @Override
    public String convertToText() {
        return super.convertToText() + super.getDivider() + super.getParseFormatDateTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.getOutputFormatDateTime() + ")";
    }
}
