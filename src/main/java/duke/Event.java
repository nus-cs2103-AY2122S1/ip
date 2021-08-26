package duke;

public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    public String saveData() {
        return "E | " + this.getStatusIcon() + " | " + description + " | " + time;
    }
}
