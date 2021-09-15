package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task class which falls under deadline category.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline instance which is one of the task's type.
     *
     * @param tags The string array consisting all the tags.
     * @param description The task description.
     * @param by The deadline of the task.
     */
    public Deadline(String[] tags, String description, LocalDate by) {
        super(tags, description);
        this.by = by;
    }

    /**
     * Returns a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    @Override
    public String toDataString() {
        return String.format("DEADLINE %s | %s", super.toDataString(), by);
    }

    /**
     * Returns a string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String taskType = "[D]";
        String taskDescription = super.toString();
        String byDate = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String taskBy = " (by: " + byDate + ")";

        return taskType + taskDescription + taskBy;
    }
}
