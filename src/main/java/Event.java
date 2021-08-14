public class Event extends Task {
    private String time;
    public Event(String desc, String time) {
        super(desc);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", time);
    }
}
