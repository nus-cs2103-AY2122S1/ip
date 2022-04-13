package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class which encapsulates a task which has
 * a deadline.
 */
public class Deadline extends Task {

    /**
     * The date representing the deadline of the task
     */
    protected LocalDate by;

    /**
     * A public constructor to initialise the deadline
     * of the task and calls the parent constructor.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string containing the given date in a
     * different format. The format is changed to
     * "MM d yyyy".
     *
     * @return The string representing the date.
     */
    public String changeDateFormat() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the letter indicating the type of the task.
     *
     * @return The letter which represents Deadline.
     */
    @Override
    public String getTaskIndicator() {
        return "D";
    }


    public LocalDate getBy() {
        return by;
    }


    /**
     * Returns the string representation of a
     * deadline task.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + changeDateFormat() + ")";
    }

}

