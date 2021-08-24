package Duke.Task;

import java.time.LocalDate;

public class Event extends Task {
    private static final String TASK_TYPE_ICON = "E";
    private static final String AT_DATE_EMPTY_MESSAGE = "Event at date cannot be empty.";

    private final LocalDate at;

    public Event(String description, LocalDate at) throws InvalidTaskException {
        super(description);
        if (at == null) throw new InvalidTaskException(AT_DATE_EMPTY_MESSAGE);
        this.at = at;
    }

    @Override
    public String toString() {
        String dateString = this.at.format(DATE_DISPLAY_FORMAT);
        return super.toString() + String.format(" (at: %s)", dateString);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
