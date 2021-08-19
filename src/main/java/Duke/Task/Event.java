package Duke.Task;

public class Event extends Task {
    private static final String TASK_TYPE_ICON = "E";
    private static final String AT_DATE_EMPTY_MESSAGE = "Event at date cannot be empty.";

    private final String at;

    public Event(String description, String at) throws InvalidTaskException {
        super(description);
        if (at.isEmpty()) throw new InvalidTaskException(AT_DATE_EMPTY_MESSAGE);
        this.at = at;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.at);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
