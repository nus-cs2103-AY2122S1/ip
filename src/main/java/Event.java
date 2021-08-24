public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description, "E");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String markDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  [E][X] %s", this.description);
    }
}
