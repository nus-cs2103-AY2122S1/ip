public class Event extends Task {
    private String eventPeriod;

    public Event(String description, String eventPeriod) throws AisuException {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String toString() {
        return String.format("[Event] %s %s (at: %s)", this.getStatusIcon(), this.description, this.eventPeriod);
    }
}
