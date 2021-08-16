public class Event extends Task {
    private final String at;

    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
