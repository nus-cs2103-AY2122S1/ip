package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task which inherits from Task and contains the dateTime for the deadline.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Deadline extends Task {
    private final LocalDate dateTime;

    /**
     * Constructor to initialize a new Deadline.
     *
     * @param description The description of the task.
     * @param dateTime The date of the deadline for the task.
     */
    public Deadline(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor to initialize a new Deadline with status.
     *
     * @param description The description of the task.
     * @param dateTime The date of the deadline for the task.
     * @param isDone The status of the task.
     */
    public Deadline(String description, LocalDate dateTime, boolean isDone) {
        super(description, isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the task in a desired format.
     *
     * @return The string representation of the task, prefixed with a status icon and deadlines identifier.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                getStatusIcon(),
                getDescription(),
                dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns the string representation of this task that is suitable for storage.
     *
     * @return The string representation of the encoded string for storage.
     */
    @Override
    public String encodeTaskForStorage() {
        int encodedIsDone = getIsDone() ? 1 : 0;
        return String.format("D | %d | %s | %s", encodedIsDone, getDescription(), dateTime);
    }
}
