package model.task;

public class Event extends Task {
    protected String startTime;
    public Event(String entry, String startTime) {
        super(entry);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startTime + ")";
    }
}
