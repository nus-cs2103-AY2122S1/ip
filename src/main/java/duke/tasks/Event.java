package duke.tasks;

public class Event extends Task{

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    Event(String description, String at, Boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return this.at;
    }

    public Event markAsDone() {
        return new Event(this.description, this.at, true);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
