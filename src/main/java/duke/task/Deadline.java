package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the task list.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private String formattedDtf;

    /**
     * Represents a constructor for the Deadline class where the description of task and date are initialized.
     *
     * @param description Description of the task.
     * @param by The date of deadline in yyyy-MM-dd HHmm format.             
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy, h a");
        String formattedDtf = this.by.format(dtf);
        this.formattedDtf = formattedDtf;
    }
    
    public LocalDateTime getDate() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task that is stored on duke.txt.
     *
     * @return String representation of the deadline task stored on duke.txt.
     */
    public String getTaskListOnDisk() {
        return "duke.task.Deadline |" + super.getStatusIcon() + "| " + description + " | by: " + formattedDtf;
    }

    /**
     * Returns the string representation of the deadline task that is stored in the task list.
     *
     * @return String description of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDtf + ")";
    }

    public String getDescription() {
        return this.description;
    }
}
