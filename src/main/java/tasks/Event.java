package tasks;

public class Event extends Task {

    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (at: " + this.at + ")";
    }
}
