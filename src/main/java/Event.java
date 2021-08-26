public class Event extends Task {
    public Event(String description, String by) {
        super(description, "Event", by);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }
}
