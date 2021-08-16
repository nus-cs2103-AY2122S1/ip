public class Event extends Task {

    private String duration;

    public Event(String taskName, String duration) {
        super(taskName);
        this.duration = duration;
    }

    public String getWhen() {
        return this.duration;
    }

    @Override
    public String displayInfo() {
        return String.format("[E] [%s] %s (at: %s)", this.getStatus(), this.getTaskName(), this.getWhen());
    }
}
