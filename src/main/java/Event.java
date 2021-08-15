public class Event extends Task {
    protected String eventTime;

    public Event(String taskDescription, String eventTime) {
        super(taskDescription);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), this.eventTime);
    }
}
