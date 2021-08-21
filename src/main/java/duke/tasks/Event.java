package duke.tasks;

public class Event extends Task {
    private String marker = "[E]";
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return this.marker + super.toString() + " (by:" + time + ")";
    }
}