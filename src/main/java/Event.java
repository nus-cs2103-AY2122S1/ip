public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        String time = this.time.length() > 0 ? (" (at: " + this.time + ")") : "";

        return "[E]" + super.toString() + time;
    }
}
