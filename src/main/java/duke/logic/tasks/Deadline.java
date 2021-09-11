package duke.logic.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * duke.logic.tasks.Task that need to be done before a specific date/time
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for the class.
     *
     * @param description Task description.
     * @param by          Desired time.
     */
    public Deadline(String description, String tag, LocalDate by) {
        super(description, tag);
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
            return "D ; 1 ; " + this.description + " ; " + this.by + " ; " + this.tag;
        } else {
            return "D ; 0 ; " + this.description + " ; " + this.by + " ; " + this.tag;
        }
    }

    /**
     * String representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " 
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" 
                + (this.tag.equals("") ? "" : " #" + this.tag);
    }
}