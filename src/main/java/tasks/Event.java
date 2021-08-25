package tasks;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * tasks.Task that start at a specific time and ends at a specific time
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for the Event class
     * 
     * @param description The event description.
     * @param at The time the event occurs.
     */
    public Event(String description, LocalDate at) {
        super(description);
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
            return "E ; 1 ; " + this.description + " ; " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            return "E ; 0 ; " + this.description + " ; " + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
    
    /**
     * String representation of this task.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
