package duke.task;

public class Event extends Task {
    private String duration;

    public Event(String name, String duration) {
        super(name);
        this.duration = duration;
    }

    public String logo() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.getName() + " (at: " + this.duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}
