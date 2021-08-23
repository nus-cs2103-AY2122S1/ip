public class Event extends Task {
    private final String at;

    public Event(String content, String at, boolean isDone) {
        super(content, isDone);
        this.at = at;
    }

    public Event(String content, String at) {
        this(content, at, false);
    }

    @Override
    public String dataFormat() {
        return String.format("E, %d, %s, %s\n", isDoneInt(), getContent(), at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}