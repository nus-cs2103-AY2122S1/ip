public class Event extends Task {
    private final String eventPeriod;

    public Event(String description, String eventPeriod) throws AisuException {
        super(description);
        this.eventPeriod = eventPeriod;
    }

    @Override
    public String ParseData() {
        return "E;;" + (this.isDone ? "1" : "0") + ";;" + this.description + ";;" + this.eventPeriod;
    }

    @Override
    public String toString() {
        return String.format("[Event] %s %s (at: %s)", this.getStatusIcon(), this.description, this.eventPeriod);
    }
}
