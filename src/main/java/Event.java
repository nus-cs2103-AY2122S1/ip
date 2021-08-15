public class Event extends Task {
    private String time = "unknown";

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + time + ")";
    }
}
