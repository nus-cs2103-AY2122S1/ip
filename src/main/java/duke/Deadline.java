package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that inherits from Task and includes a Date (the deadline of the Task)
 */
public class Deadline extends Task {

    private final LocalDateTime date;

    /**
     * Constructor.
     * @param title the Task title
     * @param date the deadline
     */
    public Deadline(String title, LocalDateTime date) {
        super(title);
        this.date = date;
    }


    /**
     * toString method.
     * @return a String.
     */
    @Override
    public String toString() {
        if (!this.getDone()) {
            return "[D][ ]" + this.getTitle()
                    + "| (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        } else {
            return "[D][X]" + this.getTitle()
                    + "| (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
        }

    }
}
