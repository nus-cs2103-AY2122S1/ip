public class Event extends Task {
    private final String when;

    public Event(String taskName, String when) {
        super(taskName);
        this.when = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + when + ")";
    }
}
