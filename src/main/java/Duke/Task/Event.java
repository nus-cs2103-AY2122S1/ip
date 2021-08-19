package Duke.Task;

public class Event extends Task {
    private static final String TASK_TYPE_ICON = "E";

    private final String period;

    public Event(String description, String period) throws EmptyDescriptionException {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (at: %s)", this.period);
    }

    @Override
    protected String getTaskTypeIcon() {
        return TASK_TYPE_ICON;
    }
}
