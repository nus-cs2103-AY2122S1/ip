package task;

public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at;
    }

    @Override
    public String serialize() {
        return String.join(" | ", "E", this.done ? "1" : "0", this.description, this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
