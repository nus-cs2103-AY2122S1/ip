import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a deadline object.
 * Deadline objects are task objects that needs to be done by a specific date/time.
 *
 * @author Dickson
 */
public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Gets string representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return "[D]" + super.toString() + " (by: " +
                dateTime.format(dateTimeFormatter) + ")";
    }
}
