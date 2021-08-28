package duke;

import java.time.LocalDate;

/**
 * A type of <code>Task</code> that has a end time denoted by <code>/by</code> in the user input.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate byDate;

    /**
     * Returns a Deadline object.
     *
     * @param description description of Deadline
     * @param by String for the time of Deadline
     * @param isDone indicates if Deadline has been completed
     * @param hasNotif indicates if a notif needs to be sent to user for the creation of this Deadline
     */
    public Deadline(String description, String by, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.by = by;
        this.category = Category.DEADLINE;
    }

    /**
     * Returns a Deadline object.
     *
     * @param description description of Deadline
     * @param byDate LocalDate for the time of Deadline
     * @param isDone indicates if Deadline has been completed
     * @param hasNotif indicates if a notif needs to be sent to user for the creation of this Deadline
     */
    public Deadline(String description, LocalDate byDate, boolean isDone, boolean hasNotif) {
        super(description, isDone);
        this.byDate = byDate;
        this.category = Category.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (by != null ? by : byDate) + ")";
    }
}
