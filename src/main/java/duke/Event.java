package duke;

/**
 * This class encapsulates an event task in the Duke Application.
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E,%s,%s", super.toSaveFormat(), this.at);
    }
}
