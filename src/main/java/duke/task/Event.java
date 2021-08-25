package duke.task;

public class Event extends Task {
    private String duration;
    private String name;

    public Event(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    public String logo() {
        return "[E]";
    }

    @Override
    public String toString() {
        return this.name + " (at: " + this.duration + ")";
    }

    public String getDuration() {
        return this.duration;
    }
}
