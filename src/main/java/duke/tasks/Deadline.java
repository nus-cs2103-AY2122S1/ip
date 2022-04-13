package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    /** Date and time of deadline. */
    private final LocalDateTime by;

    /**
     * Constructor of Deadline.
     *
     * @param description description of task.
     * @param by date and time of deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Parses the Deadline to an appropriate format for saving.
     *
     * @return a string containing only important information of the Deadline to be saved.
     */
    @Override
    public String parseToSave() {
        return "D|" + super.parseToSave() + "|" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    /**
     * toString method of Deadline.
     *
     * @return toString description of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }
}
