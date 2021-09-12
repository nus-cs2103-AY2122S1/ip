package taubot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. A <code>Deadline</code> object corresponds to
 * a task represented by a due date and time.
 */
public class Deadline extends Task {

    protected final LocalDate date;
    protected final String time;
    /**
     * Constructor for <code>Deadline</code> object.
     *
     * @param description Description of the deadline task.
     * @param ld The due date of the task represented by a <code>LocalDate</code> object.
     * @param time The due time of the task represented by a <code>String</code> object.
     */
    public Deadline(String description, LocalDate ld, String time) {
        super(TaskType.DEADLINE, description);
        this.date = ld;
        this.time = time;
    }
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns string representation of <code>Deadline</code> object.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy ")) + this.time + ")";
    }

    /**
     * Returns string representation of <code>Deadline</code> object's due date.
     *
     * @return String representation of due date.
     */
    public String getBy() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " " + this.time;
    }
}
