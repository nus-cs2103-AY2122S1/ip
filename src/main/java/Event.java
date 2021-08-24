public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public char getTaskType() { return 'E'; }

    @Override
    public String getTime() {
        return this.at;
    }

    @Override
    public String toString() {
        return "  [E]" + super.toString() + " (at: " + at + ")";
    }
}
