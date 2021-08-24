package Duke.Task;

public class Event extends Task {
    public static final String TASK_TYPE_ICON = "E";
    private static final String AT_DATE_EMPTY_MESSAGE = "Event at date cannot be empty.";

    private final String at;

    public Event(String description, String at, boolean isDone) throws InvalidTaskException {
        super(description, isDone);
        if (at.isEmpty()) throw new InvalidTaskException(AT_DATE_EMPTY_MESSAGE);
        this.at = at;
    }

    public Event(String description, String at) throws InvalidTaskException {
        this(description, at, false);
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.at);
    }

    @Override
    public String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
