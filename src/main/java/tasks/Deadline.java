package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * tasks.Task that need to be done before a specific date/time
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Return string representation of the task to write to hard disk.
     *
     * @return The string representation.
     */
    @Override
    public String toSaveInHardDisk() {
        if (this.isDone) {
            return "D ; 1 ; " + this.description + " ; " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return "D ; 0 ; " + this.description + " ; " + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    /**
     * String representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}