package duke.data.task;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that represents an Event task.
 *
 * @author Won Ye Ji
 */
public class Event extends Task {

    protected String at;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyy HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd h:mm a");

    /**
     * Constructor for Event class.
     *
     * @param description Description of the task.
     * @param at Date of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats the event date to the desirable format.
     *
     * @param at Input date format.
     * @return A string representing the date in the desirable format.
     */
    public String dateFormatter(String at) throws DateTimeParseException {
        try {
            LocalDateTime date = LocalDateTime.parse(at, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            return "Format error";
        }
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateFormatter(at) + ")";
    }

    /**
     * Returns the string representation of the Event task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public String toSave() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
