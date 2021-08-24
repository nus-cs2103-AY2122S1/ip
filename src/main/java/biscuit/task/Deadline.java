package biscuit.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class: For tasks that need to be done before a specific date/time.
 * eg. submit report by 11/10/2019 5pm.
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Constructs Deadline class.
     *
     * @param description Task description.
     * @param date        Date task is due.
     */
    public Deadline(String description, LocalDate date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
    }

    /**
     * Constructs Deadline class.
     * Used when need to set isDone.
     *
     * @param isDone      Boolean of if task is done.
     * @param description Task description.
     * @param date        Date task is due.
     */
    public Deadline(boolean isDone, String description, LocalDate date) {
        super(isDone, description, TaskType.DEADLINE);
        this.date = date;
    }

    /**
     * Gets Task date.
     *
     * @return Task date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns string representation of Deadline.
     *
     * @return Deadline String.
     */
    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + " (by: "
                + date.format(DateTimeFormatter.ofPattern("dd LLL yyyy")) + ")";
    }
}
