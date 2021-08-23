public class Event extends Task {
    private final String duration;

    public Event(String taskName, String duration) {
        super(taskName);
        this.duration = duration.trim();
    }

    public Event(String taskName, boolean isDone, String duration) {
        super(taskName, isDone);
        this.duration = duration.trim();
    }

    public String getDuration() {
        return this.duration;
    }

    @Override
    public String getFileString() {
        return String.format("E | %s | %s", super.getFileString(), this.duration);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.duration);
    }
}
