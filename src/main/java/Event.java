public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTypeIndicator() {
        return "[E]";
    }

    @Override
    public String toFileRecord() {
        return String.format("E | %d | %s | %s",
                this.isDone ? 1 : 0 , this.description, this.at);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}