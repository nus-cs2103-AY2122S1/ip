package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dateTime;
    
    /**
     * Constructor for deadline.
     *
     * @param description description of the deadline
     * @param dateTime due date and time of the deadline
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
