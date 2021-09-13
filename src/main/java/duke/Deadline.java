package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline task.
 *
 * @author Kleon Ang
 */
public class Deadline extends Task {
    private static final String taskBadge = "[D]";
    private final LocalDateTime by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description A string describing the Deadline task.
     * @param by A LocalDateTime indicating when the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a data string of the Deadline task for saving.
     *
     * @return A data string including the Deadline's icon, description and due date.
     */
    @Override
    public String getDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d H:mm");
        return "D | " + super.getDataString() + " | " + this.by.format(formatter);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string including the Deadline's icon, description and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return taskBadge + super.toString() + "\n  (by: " + this.by.format(formatter) + ")";
    }
}
