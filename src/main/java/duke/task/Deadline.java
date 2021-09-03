package duke.task;

import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;

/**
 * Represents a deadline with a description and a date that it is due by.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     * Creates a Deadline with description and a date.
     *
     * @param description Description of task to be done by due date.
     * @param by Date that the task is due by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string identifier for a Deadline.
     * String identifier for Deadline is "D".
     *
     * @return String identifier for Deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the date that the task represented by this Deadline is due by.
     *
     * @return Date that the task represented by this Deadline is due by.
     */
    @Override
    public LocalDate getTime() {
        return this.by;
    }

    /**
     * Returns a formatted string that starts with this Deadline's string identifier, followed by
     * its done status and description, and finally its due date.
     *
     * @return Formatted description of this Deadline.
     */
    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: " + Parser.parseLocalDate(this.by) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Indicates whether another object is equals to this Deadline.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equals to this Deadline.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;

            // Check if done status, description and time are the same.
            boolean isDoneStatusSame = this.isDone == other.isDone;
            boolean isDescriptionSame = this.description.equals(other.description);
            boolean isTimeSame = this.by.equals(other.by);

            return (isDoneStatusSame && isDescriptionSame && isTimeSame);
        }
        return false;
    }
}
