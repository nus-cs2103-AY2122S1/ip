package duke.data.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    public String formatToWrite() {
        return String.format("E | %s | %s", super.formatToWrite(), this.at);
    }
}