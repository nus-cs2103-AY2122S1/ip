package duke.task;

public class Event extends Task {
    private String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String convertToString() {
        return "E|" + super.convertToString() + "|" + duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + duration + ")";
    }
}
