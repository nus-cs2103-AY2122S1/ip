package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Storage;

/**
 * A task with a description and an end date/time
 */
public class Deadline extends Task {

    private LocalDate endDate;

    /**
     * Constructs a new Deadline with description, completion status
     * and completion date.
     * @param description description for the deadline
     * @param completed completion status of the deadline
     * @param endDate day on which this deadline is due
     */
    public Deadline(String description, boolean completed, LocalDate endDate) {
        super(description, completed);
        this.endDate = endDate;
    }

    public boolean isBefore(LocalDate date) {
        return this.endDate.isBefore(date);
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return 1;
        }
        if (task instanceof Deadline) {
            return this.isBefore(((Deadline) task).endDate) ? -1 : 1;
        }
        return ((Event) task).isBefore(this.endDate) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @Override
    public String encode() {
        return "d" + Storage.DELIMITER
                + (this.isCompleted() ? "1" : "0") + Storage.DELIMITER
                + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + Storage.DELIMITER
                + this.getDescription();
    }
}
