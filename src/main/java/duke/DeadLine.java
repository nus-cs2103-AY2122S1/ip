package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This class represents a Deadline.
 *  A Deadline: tasks that need to be done before a specific date/time.
 *
 * @author Ryan Tian Jun.
 */
public class DeadLine extends Task {

    private String by;
    private LocalDate date;

    /**
     * This constructor handles the creation af a new Deadline Task.
     *
     * @param description Task description.
     * @param type Task type: Deadline.
     * @param by Deadline due date.
     */
    public DeadLine(String description, Type type, String by) {
        super(description, type);
        try {
            LocalDate d1 = LocalDate.parse(by);
            this.date = d1;
            this.by = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException dateTimeParseException) {
            this.by = by;
        }
    }

    /**
     * This constructor handles the Deadline Task read from the hard drive.
     *
     * @param type Task type: Deadline.
     * @param isDone Whether the task has been done or not.
     * @param description Task description.
     * @param by Deadline due date.
     */
    public DeadLine(Type type, boolean isDone, String description, String by) {
        super(type, isDone, description);
        this.by = by;
    }

    /**
     * Returns the stored deadline.
     *
     * @return returns the stored Deadline of the task.
     */
    @Override
    public String getTime() {
        return by;
    }

    /**
     * Returns the String representation of a Deadline
     * as displayed in the list/mark done.
     *
     * @return returns the String representation of a Deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
