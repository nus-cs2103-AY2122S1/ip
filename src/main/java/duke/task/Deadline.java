package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a duke.task that has a date to be done by.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Deadline extends Task {

    private final LocalDateTime date;

    /**
     * Constructor for Deadline duke.task.
     *
     * @param isDone      true if the duke.task is done
     * @param description the description
     * @param date        the date for which the Deadline is due on
     */
    public Deadline(boolean isDone, String description, String date) {
        super(description, isDone);
        this.date = LocalDateTime.parse(date.replace(" ", ""),
                DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm"));
    }

    /**
     * Returns the string representation of the Deadline duke.task.
     *
     * @return the string representation of the Deadline duke.task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeFormatter.ofPattern("d MMM uuuu h.mma").format(this.date) + ")";
    }

    /**
     * Returns if the duke.task date is equal to the date provided.
     *
     * @param date the date provided
     * @return true if they are both equal
     */
    @Override
    public boolean onDate(LocalDate date) {
        return this.date.toLocalDate().equals(date);
    }
}
