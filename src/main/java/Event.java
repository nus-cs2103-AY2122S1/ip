public class Event extends ToDo {
    private final String duration;

    public Event(String taskName, String duration) {
        super(taskName);
        this.duration = duration.trim();
    }

    public String getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), duration);
    }
}
