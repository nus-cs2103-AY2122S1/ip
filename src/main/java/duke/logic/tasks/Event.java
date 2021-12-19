package duke.logic.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task that start at a specific time and ends at a specific time
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for the Event class
     *
     * @param description The event description.
     * @param at          The time the event occurs.
     */
    public Event(String description, String tag, LocalDate at) {
        super(description, tag);
        this.at = at;
    }

    /**
     * Return string representation of the task to write to hard disk.
     *
     * @return The string representation.
     */
    @Override
    public String toSaveInHardDisk() {
        if (this.isDone) {
            return "E ; 1 ; " + this.description + " ; " + this.at + " ; " + this.tag;
        } else {
            return "E ; 0 ; " + this.description + " ; " + this.at + " ; " + this.tag;
        }
    }

    /**
     * String representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + 
                at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                + (this.tag.equals("") ? "" : " #" + this.tag);
    }

}
