package duke.data.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public String dateFormatter(String by) {
        LocalDateTime date = LocalDateTime.parse(by, input);
        return date.format(output);
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
}