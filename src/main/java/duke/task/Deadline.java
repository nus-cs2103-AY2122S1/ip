package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a Task with a due date.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Initialises a Deadline with a description and due date.
     *
     * @param description a description String for Deadline.
     * @param byDate the due date of the Deadline.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Initialises a Deadline with a description and due date and time.
     *
     * @param description a description String for Deadline.
     * @param byDate the due date of the Deadline.
     * @param byTime the due time of the Deadline.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    @Override
    public LocalDate getDate() {
        return byDate;
    }

    @Override
    public String toString() {
        if (byTime != null) {
            return "[D]" + super.toString() + " (by: "
                    + byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    + ", " + byTime + ")";
        }
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
