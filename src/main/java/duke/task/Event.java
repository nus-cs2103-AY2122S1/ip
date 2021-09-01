package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the task list.
 */
public class Event extends Task {
    protected LocalDateTime at;
    private String formattedDtf;

    /**
     * Constructor for the Event class where the description of task and date are initialized.
     *
     * @param description Description of the task.
     * @param at The date of the event in yyyy-MM-dd HHmm format.             
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.at.format(dtf);
        this.formattedDtf = formattedDtf;
    }
    
    /**
     * Returns the string representation of the event task stored on duke.txt.
     *
     * @return String representation of the event task stored on duke.txt.
     */
    public String getTaskListOnDisk() {
        return "duke.task.Event |" + super.getStatusIcon() + "| " + description + " | by: " + formattedDtf;
    }

    /**
     * Returns the string representation of the event task stored in the list variable.
     *
     * @return String description of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDtf + ")";
    }
}
