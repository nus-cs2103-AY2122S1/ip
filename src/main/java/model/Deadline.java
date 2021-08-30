package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task containing description and the deadline of the task.
 */
public class Deadline extends Task implements TimedItem {
    /** Deadline stored in local date time */
    private final LocalDateTime deadline;
    
    /**
     * Constructor for Deadline.
     *
     * @param desc Description.
     * @param deadline LocalDateTime.
     */
    public Deadline(String desc, LocalDateTime deadline) {
        super(desc);
        this.deadline = deadline;
    }
    
    /**
     * Returns string representation of deadline, marked with [D], desc and the deadline.
     *
     * @return string.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
    
    @Override
    public LocalDateTime getTime() {
        return deadline;
    }
}
