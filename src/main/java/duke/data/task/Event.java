package duke.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents an Event task.
 *
 * @author Won Ye Ji
 */
public class Event extends Task {

    protected String at;
    private final DateTimeFormatter input = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd h:mm a");

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
    public String dateFormatter(String at) {
        LocalDateTime date = LocalDateTime.parse(at, input);
        return date.format(output);
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

    public String toSave() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}