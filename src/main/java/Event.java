public class Event extends Task{
    private String time;

    public Event(String task, String time) {
        super(task, "E");
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.getTaskType(), this.getCompletedMarker(), this.getTask(), this.time);
    }
}
