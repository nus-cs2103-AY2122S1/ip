package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A wrapper for a duke.task.Deadline which is a duke.task.Task that needs to be done before a date/time.
 *
 * @author Wong Yun Rui Chris
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byLD;

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
            byLD = LocalDate.parse(by);
        } catch (DateTimeException e) {
            byLD = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (byLD != null
                    ? byLD.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    : this.by)
                + ")";
    }

    @Override
    public String toData() {
        return "[D] | " + super.toData() + " | " + this.by;
    }
}
// deadline task1 /by 22/08/2021 6:40pm