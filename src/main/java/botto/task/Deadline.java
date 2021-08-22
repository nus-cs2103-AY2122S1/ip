package botto.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Format for the Botto bot's deadline task
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor of a deadline task
     *
     * @param description description of the deadline task
     * @param by date for the deadline task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * return string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }


}