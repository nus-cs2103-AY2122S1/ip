public class Event extends Task {
    private final String at;

    public Event(String content, String at) {
        super(content);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}