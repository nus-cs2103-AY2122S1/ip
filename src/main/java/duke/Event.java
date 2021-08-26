package duke;

public class Event extends Task {
    public String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return this.getTaskIcon() + "//" + this.getStatusIcon() + "//" + description + "//" + time;
    }
}
