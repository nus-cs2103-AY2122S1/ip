package duke.logic.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import duke.logic.commands.UpdateCommand;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private static final String START_DATE_FORMAT = "dd MMM yyyy h.mma";
    private static final String END_DATE_FORMAT = "dd MMM yyyy h.mma";
    private static final Locale REGION = Locale.UK;

    private LocalDateTime at;
    private LocalDateTime end;

    /**
     * Creates a new event task.
     *
     * @param description The task description.
     * @param isDone The status of the task.
     * @param at The start date time of the task.
     * @param end The end date time of the task.
     */
    public Event(String description, boolean isDone, LocalDateTime at, LocalDateTime end) {
        super(description, isDone);
        this.at = at;
        this.end = end;
    }

    /**
     * Creates a new event task that is <em>not</em> done yet.
     *
     * @param description The task description.
     * @param at The start date time of the task.
     * @param end The end date time of the task.
     */
    public Event(String description, LocalDateTime at, LocalDateTime end) {
        this(description, false, at, end);
    }

    @Override
    public Task createUpdatedCopy(UpdateCommand.UpdateTaskDescriptor updateDescriptor) {
        String updatedDescription = updateDescriptor.getDescription().orElse(this.getDescription());
        LocalDateTime updatedAt = updateDescriptor.getAt().orElse(this.at);
        LocalDateTime updatedEnd = updateDescriptor.getEnd().orElse(this.end);
        return new Event(updatedDescription, getIsDone(), updatedAt, updatedEnd);
    }

    @Override
    public String getSaveFormat() {
        return "E" + super.getSaveFormat() + " | " + at + " | " + end;
    }

    @Override
    public String toString() {
        String s = "[E]" + super.toString();
        if (end == null) {
            s += " (at: " + at.format(DateTimeFormatter.ofPattern(START_DATE_FORMAT, REGION)) + ")";
        } else {
            s += " (from: " + at.format(DateTimeFormatter.ofPattern(START_DATE_FORMAT, REGION))
                    + " — " + end.format(DateTimeFormatter.ofPattern(END_DATE_FORMAT, REGION)) + ")";
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return getDescription().equals(other.getDescription())
                && getIsDone() == other.getIsDone()
                && at.equals(other.at)
                && end.equals(other.end);
    }
}
