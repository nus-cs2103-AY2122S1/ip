package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Deadline (Task). Can be added to list in Duke.
 *
 * @author Ruth Poh (Lab 10H)
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor to initialize Deadline.
     *
     * @param taskstr Task.
     * @param deadline Deadline of task.
     */
    public Deadline(String taskstr, LocalDate deadline) {
        super(taskstr);
        this.by = deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        super.date = deadline;
    }

    @Override
    public String getTime() {
        return this.by;
    }

    @Override
    public String getTimeStorage() {
        return this.date.toString();
    }

    /**
     * Returns string of Deadline (Task).
     * @return string of Deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + this.by + ")";
    }

}
