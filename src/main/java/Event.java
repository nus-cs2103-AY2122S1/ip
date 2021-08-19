public class Event extends Task {
    protected String eveAtTime;

    public Event(String eventName, String atTime) {
        super(eventName);
        this.eveAtTime = atTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eveAtTime + ")";
    }
}
