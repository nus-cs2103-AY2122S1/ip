import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline task.
 *
 * @author Kleon Ang
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description A string describing the Deadline task.
     * @param by A LocalDateTime indicating when the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string including the Deadline's icon, description and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }
}
