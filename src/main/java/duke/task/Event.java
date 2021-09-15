package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** A task class which falls under event category. */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructs an Event instance which is one of the task's type.
     *
     * @param tags The string array consisting all the tags.
     * @param description The task description.
     * @param at The date of the task being carried out.
     */
    public Event(String[] tags, String description, LocalDate at) {
        super(tags, description);
        this.at = at;
    }

    /**
     * Returns a string representation in the format to be written in tasks.txt file.
     *
     * @return The string representation in the format to be written in tasks.txt file.
     */
    @Override
    public String toDataString() {
        return String.format("EVENT %s | %s", super.toDataString(), at);
    }

    /**
     * Returns a string representation of this task.
     *
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String taskType = "[E]";
        String taskDescription = super.toString();
        String atDate = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String taskAt = " (at: " + atDate + ")";

        return taskType + taskDescription + taskAt;
    }
}
