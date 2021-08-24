package duke;

public class Event extends Task {
    String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        super(description,
                isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String saveString() {
        return String.format("E|%s|%s|%s",
                super.description,
                this.at,
                super.isDone ? "1" : "0");
    }

}
