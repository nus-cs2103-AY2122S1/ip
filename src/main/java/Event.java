public class Event extends Task {
    private String timestamp;

    public Event(String name, String timestamp) {
        super(name);
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timestamp);
    }
}
