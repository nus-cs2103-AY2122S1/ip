package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that has a date to be done by.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class Deadline extends Task {

    private final LocalDateTime DATE;

    /**
     * Constructor for Deadline task.
     *
     * @param isDone      true if the task is done
     * @param description the description
     * @param date        the date for which the Deadline is due on
     * @param priority    the priority of the task
     */
    public Deadline(boolean isDone, String description, String date, int priority) {
        super(description, isDone, priority);
        this.DATE = LocalDateTime.parse(date.replace(" ", ""),
                DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm"));
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return the string representation of the Deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeFormatter.ofPattern("d MMM uuuu h.mma").format(this.DATE) + ")";
    }

    /**
     * Returns if the task date is equal to the date provided.
     *
     * @param date the date provided
     * @return true if they are both equal
     */
    @Override
    public boolean onDate(LocalDate date) {
        return this.DATE.toLocalDate().equals(date);
    }
}
