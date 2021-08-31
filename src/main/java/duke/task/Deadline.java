package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A wrapper for a duke.task.Deadline which is a duke.task.
 * Task that needs to be done before a date/time.
 *
 * @author Wong Yun Rui Chris
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byLd;

    /**
     * A public constructor to initialise a duke.task.Deadline duke.task.Task.
     *
     * @param description The String description/name of the task
     * @param by The String describing when the duke.task.Deadline need to be done by
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
        try {
            byLd = LocalDate.parse(by);
        } catch (DateTimeException e) {
            byLd = null;
        }
    }

    /**
     * Returns the string representation of this Deadline task that is to be displayed
     * by Duke. It comprises the tag for Deadline, description of this Deadline and the
     * deadline of this task.
     *
     * @return The string representation of this Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (byLd != null
                    ? byLd.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    : this.by)
                + ")";
    }

    /**
     * Returns the data representation for this Deadline task that is to be saved
     * and used on initial execution of Duke.
     *
     * @return The String representation of the data of this Deadline task
     */
    @Override
    public String toData() {
        return "[D] | " + super.toData() + " | " + this.by;
    }
}
