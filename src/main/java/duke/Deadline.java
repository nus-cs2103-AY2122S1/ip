package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of <code>Task</code> that has a end time denoted by <code>/by</code> in the user input.
 */
public class Deadline extends Task {

    protected static final DateTimeFormatter PRINTDATEFORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected String by;
    protected LocalDate byDate;

    /**
     * Returns a Deadline object.
     *
     * @param description description of Deadline
     * @param by String or LocalDate for the time of Deadline
     * @param isDone indicates if Deadline has been completed
     */
    public Deadline(String description, Object by, boolean isDone) {
        super(description, isDone);
        if (by instanceof String) {
            this.by = (String) by;
        } else {
            assert by instanceof LocalDate : "has to be LocalDate";
            this.byDate = (LocalDate) by;
        }
        this.category = Category.DEADLINE;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (by != null ? by : byDate.format(PRINTDATEFORMAT)) + ")";
    }
}
