package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, a subtype of Task. A Deadline also includes a deadline date, stored as a LocalDate.
 *
 * @author Hanif Kamal
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Class constructor.
     *
     * @param taskName The name or description of the Deadline.
     * @param isDone Whether or not the Deadline is done.
     * @param deadline The date of the Deadline.
     */
    public Deadline(String taskName, boolean isDone, LocalDate deadline) {
        super(taskName, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return The string representation of the Deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "[D]" + super.toString() + " (by: " + this.deadline.format(dtf) + ")";
    }
}
