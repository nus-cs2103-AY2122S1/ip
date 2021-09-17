package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Class Constructor with the description and time of the task.
     *
     * @param description description of the task.
     * @param by time the task needs to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the String representation of a Deadline task.
     *
     * @return String representation of a Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the String representation of a Deadline task for storage purpose.
     *
     * @return String representation of a Deadline task for storage purpose.
     */
    @Override
    public String toHistory() {
        return "E" + super.toHistory() + " | " + this.by;
    }
}
