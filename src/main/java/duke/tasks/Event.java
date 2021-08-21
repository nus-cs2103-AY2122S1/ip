package duke.tasks;

public class Event extends Task {
    private String marker = "E";
    private String time;

    public Event(String name, String time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String getTime() {
        return this.time;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString() + " (by:" + time + ")";
    }
}