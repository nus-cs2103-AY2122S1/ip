package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A Task of type Deadline
 */
public class Deadline extends Task{
    /**
     * When is the deadline due on?
     */
    protected LocalDateTime by;

    /**
     * The Constructor
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Returns the string representation of a Deadline
     * @return string representation of a Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
