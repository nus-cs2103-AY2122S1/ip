public class Event extends Task {
    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        String stem = super.toString();
        return String.format("[D]%s (at: %s)", stem, this.eventTime);
    }
}