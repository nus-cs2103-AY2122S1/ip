package duke.commands;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Encapsulates a task with a deadline.
 * Similar to a task, a task with a deadline has a description,
 * but also contains the deadline for the task.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Deadline extends Task {
    /** Deadline of the task */
    protected LocalDateTime by;

    /**
     * Constructor for creating a deadline task
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, "D");
        this.by = by;
    }

    /**
     * Returns deadline of Deadline task.
     * Represented in a LocalDateTime format.
     *
     * @return deadline of task.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns string representation of Deadline task.
     * Method override from toString() of parent class Task.
     *
     * @return string representation of Deadline.
     */
    @Override
    public String toString() {
        String month = this.by.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int date = this.by.getDayOfMonth();
        int year = this.by.getYear();
        int hour = this.by.getHour();
        int minute = this.by.getMinute();

        String toPrint = String.format("[D]%s (by: %s %d %d %s:%s)",
                super.toString(), month, date, year, hour, minute);
        return toPrint;
    }

    /**
     * Marks task as done, and returns string representation of deadline task being marked done.
     * Method override from markDone() of parent class Task.
     *
     * @return string representation of deadline task being marked done.
     */
    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [D][X] %s", this.description);
    }
}
