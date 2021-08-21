package duke.task;

public class Event extends Task {

    String at;

    public Event(String details, String at) {
        super(details);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
