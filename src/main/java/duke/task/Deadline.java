package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that require a due date.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Class constructor.
     *
     * @param description Description of the task.
     * @param by Deadline task must be done by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Alternative constructor for initializing from storage.
     *
     * @param isDone Whether the task is already checked.
     * @param description Description of the task.
     * @param by Deadline task must be done by.
     */
    public Deadline(boolean isDone, String description, LocalDate by) {
        super(isDone, description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Returns a string representation of the Deadline to be saved as.
     *
     * @return String representation of the deadline to be saved as.
     */
    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + by;
    }
}
