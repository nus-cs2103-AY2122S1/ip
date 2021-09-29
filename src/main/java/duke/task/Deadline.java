package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline with the provided description and date/time.
     * @param description The description of the Deadline task.
     * @param by The date/time the Deadline needs to be done before.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Converts the Deadline into a String that represents the Deadline.
     * @return The String representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format(
            "[%s]%s (by: %s)",
            TaskType.DEADLINE.toString(),
            super.toString(),
            this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    /**
     * Converts the Deadline into a String to be stored in Storage.
     * @return String to be stored
     */
    @Override
    public String toStorageString() {
        String taskString = super.toStorageString();
        return TaskType.DEADLINE.toString()
                + Task.STORAGE_STRING_DELIMITER
                + taskString
                + Task.STORAGE_STRING_DELIMITER
                + this.by;
    }
}
