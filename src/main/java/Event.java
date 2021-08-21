public class Event extends Task {
    private String timestamp;

    public Event(String name, String timestamp) {
        super(name);
        this.timestamp = timestamp;
    }

    @Override
    public String toText() {
        String[] props = new String[]{"E", super.getStatusIcon(), super.getName(), this.timestamp};
        return String.join(" | ", props);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timestamp);
    }
}
