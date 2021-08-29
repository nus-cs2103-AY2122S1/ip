package duke.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that represents a Deadline task.
 *
 * @author Won Ye Ji
 */
public class Deadline extends Task {

    protected String by;
    private final DateTimeFormatter input = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    private final DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd h:mm a");

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the task.
     * @param by Date the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats the due date to the desirable format.
     *
     * @param by Input date format.
     * @return A string representing the date in the desirable format.
     */
    public String dateFormatter(String by) throws DateTimeParseException {
        try {
            LocalDateTime date = LocalDateTime.parse(by, input);
            return date.format(output);
        } catch (DateTimeParseException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatter(by) + ")";
    }

    /**
     * Returns the string representation of the Deadline task to be saved in Storage.
     *
     * @return String representation of the task.
     */
    public String toSave() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}