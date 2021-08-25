public class Event extends Task{
    private String time;

    public Event(String task, String time) {
        super(task, "E");
        this.time = time;
    }

    public Event(String task, boolean completed, String time) {
        super(task, completed, "E");
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.getTaskType(), this.getCompletedMarker(), this.getTask(), this.time);
    }

    @Override
    public String parseForStorage() {
        return String.format("%s | %d | %s | %s", this.getTaskType(), this.getCompleted() ? 1 : 0, this.getTask(), this.time);
    }
}
