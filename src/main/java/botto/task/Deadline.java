package botto.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Format for the Botto bot's deadline task.
 */
public class Deadline extends Task implements Comparable<Deadline> {

    protected LocalDateTime dateTime;


    /**
     * Constructor of a deadline task.
     *
     * @param description description of the deadline task.
     * @param dateTime dateTime for the deadline task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Return string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a", Locale.getDefault());
        return "[D]" + super.toString() + " (by: " + formatter.format(dateTime) + ")";
    }


    /**
     * Compare the two deadlines.
     *
     * @param o another deadline.
     * @return the value of this deadline compared to other deadline.
     */
    @Override
    public int compareTo(Deadline o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}
