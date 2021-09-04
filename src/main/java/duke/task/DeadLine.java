package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DeadLine class for implementing tasks that have a deadline.
 * Only accepts date inputs of the format yyyy-mm-dd.
 *
 * @author Benjamin Lui
 */
public class DeadLine extends Task {

    protected LocalDate by;

    /**
     * Constructor of the Deadline class.
     * @param description the name of the task
     * @param by when is the dateline of the task in the format yyyy-mm-dd
     */
    public DeadLine(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by.trim());
    }

    /**
     * Constructor fo the Deadline class.
     * @param description name of the task
     * @param by when is the deadline of the task in the format yyyy-mm-dd
     * @param isDone whether the deadline task is done, based on its status icon
     */
    public DeadLine(String description, String by, String isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by.trim());
    }

    @Override
    public String toString() {
        String formattedDate = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
