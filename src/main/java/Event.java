public class Event extends Task {
    String by;

    public Event(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at:%s)", super.toString(), this.by);
    }
}
