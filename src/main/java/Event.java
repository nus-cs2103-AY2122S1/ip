/**
 * Event class.
 * Used to represent a event task.
 *
 * @author KelvinSoo
 * @version Level-4
 *
 */
public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(String description, String eventTime, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.eventTime = eventTime;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String getMetaData() {
        return String.format("E|%s|%s", super.getMetaData(), eventTime);
    }

    @Override
    public String getDescription() {
        return String.format("%s (at: %s)", super.getDescription(), this.eventTime);
    }
}
