package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The Deadline class encapsulates a task that has a deadline.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private LocalDate by;

    /**
     * Constructs a deadline object that is not completed yet.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @throws DukeException If the deadline of the task is not in YYYY-MM-DD format.
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
     * Constructs a deadline object with a specifiable completion status.
     *
     * @param description The description of the task.
     * @param isDone A boolean indicating whether the task has been completed.
     * @param by The deadline of the task.
     * @throws DukeException If the deadline of the task is not in YYYY-MM-DD format.
     */
    public Deadline(String description, boolean isDone, String by) throws DukeException {
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
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + ")";
    }

    /**
     * Returns the format in which the task is stored in the save file.
     *
     * @return A string representing how the task is saved.
     */
    @Override
    public String getSaveFormat() {
        return "D|" + super.getSaveFormat() + "|" + this.by + '\n';
    }

    /**
     * Checks whether another object is equal with this deadline task.
     *
     * @param other The object being compared to.
     * @return true if both are deadline tasks and share the same date, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Deadline) {
            Deadline otherDeadline = (Deadline) other;
            return super.equals(otherDeadline) && this.by.equals(otherDeadline.by);
        } else {
            return false;
        }
    }
}
