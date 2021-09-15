package duke.task;

import java.time.LocalDate;

import duke.Parser;
import duke.exception.DukeException;

/**
 * Represents a deadline with a description and a date that it is due by.
 */
public class Deadline extends TimedTask {
    /**
     * Constructor for Deadline.
     * Creates a Deadline with description and a date.
     *
     * @param description Description of task to be done by due date.
     * @param by Date that the task is due by.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
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
     * Returns a formatted string that starts with this Deadline's string identifier, followed by
     * its done status and description, and finally its due date.
     *
     * @return Formatted description of this Deadline.
     */
    @Override
    public String toString() {
        try {
            return "[" + this.getTaskType() + "]"
                    + super.toString() + " (by: " + Parser.parseLocalDate(super.date) + ")";
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

}
