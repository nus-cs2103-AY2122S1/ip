public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    
    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }

    @Override
    public String toBackupFormat() {
        return String.format("E|%s|%s", super.toBackupFormat(), at);
    }
}
