package model.task;

public class Event extends Task {
    protected String startTime;
    public Event(String entry, Boolean status, String startTime) {
        super(entry, status);
        this.startTime = startTime;
    }

    public Event(String entry, String startTime) {
        this(entry, false, startTime);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime + ")";
    }
}
