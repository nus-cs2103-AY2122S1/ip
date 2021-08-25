package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class encapsulates a task that has a deadline.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private LocalDate by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadlines should be in the form YYYY-MM-DD.");
        }
    }

    /**
     * Constructor for the Deadline class.
     *
     * @param description The description of the task.
     * @param isDone A boolean indicating whether the task has been completed.
     * @param by The deadline of the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Deadlines should be in the form YYYY-MM-DD.");
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "D|" + super.getSaveFormat() + "|" + this.by + '\n';
    }
}
