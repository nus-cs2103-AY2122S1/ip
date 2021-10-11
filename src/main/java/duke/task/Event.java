package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    public static final String TASK_TYPE_ICON = "E";
    private static final String AT_DATE_EMPTY_MESSAGE = "Event at date cannot be empty.";

    private final LocalDate at;

    /**
     * Constructs a new Event object.
     *
     * @param description The description of the task.
     * @param at The date of the event.
     * @param isDone The completion status of the task.
     * @throws InvalidTaskException If description or at is invalid;
     */
    public Event(String description, LocalDate at, boolean isDone) throws InvalidTaskException {
        super(description, isDone);
        if (at == null) {
            throw new InvalidTaskException(AT_DATE_EMPTY_MESSAGE);
        }
        this.at = at;
    }

    public Event(String description, LocalDate at) throws InvalidTaskException {
        this(description, at, false);
    }

    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        String dateString = this.at.format(DATE_DISPLAY_FORMAT);
        return super.toString() + String.format(" (at: %s)", dateString);
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
